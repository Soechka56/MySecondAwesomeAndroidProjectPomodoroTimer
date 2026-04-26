import com.example.data.mapper.UserDataMapper
import com.example.data.repository.UserRepositoryImpl
import com.example.domain.model.UserInfo
import com.example.domain.repository.OperationError
import com.example.domain.repository.ResultOfOperation
import com.example.domain.repository.UserRepository
import com.example.network.PomodoroApi
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.test.runTest
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


class UserRepositoryImplTest {
    private lateinit var mockWebServer: MockWebServer
    private lateinit var userRepository: UserRepository

    @BeforeEach
    fun setUp() {
        mockWebServer = MockWebServer()
        mockWebServer.start()

        val retrofit = Retrofit.Builder()
            .baseUrl(mockWebServer.url("/"))
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        userRepository = UserRepositoryImpl(
            retrofit.create(PomodoroApi::class.java),
            userDataMapper = UserDataMapper(),
        )
    }

    @Test
    fun `showUser should parse network response and send correct model`() = runTest {
        val expectedJson = """
            {
              "id": 0,
              "username": "test_user",
              "full_name": "Test User",
              "avatar_url": null,
              "email" : "test email",
              "created_at": "...",
              "updated_at": "..."
            }
        """.trimIndent()

        mockWebServer.enqueue(
            MockResponse().setBody(expectedJson)
                .setResponseCode(200)
        )

        val result = userRepository.showProfile(0)
        val request = mockWebServer.takeRequest(1, TimeUnit.SECONDS)

        assertThat(request?.path).isEqualTo("/users/0")
        assertThat(result).isEqualTo(
            ResultOfOperation.Success(
                UserInfo(
                    id = 0,
                    username = "test_user",
                    fullName = "Test User",
                    avatarUrl = null,
                    email = "test email",
                    createdAt = "...",
                    updatedAt = "..."
                )
            )
        )
    }

    @Test
    fun `errorMapper should return error when response code is not success`() = runTest {

        suspend fun makeErrorRequest(code: Int): ResultOfOperation<UserInfo> {
            mockWebServer.enqueue(
                MockResponse().setResponseCode(code)
            )
            return userRepository.showProfile(1)
        }


        assertThat(makeErrorRequest(401)).isEqualTo(
            ResultOfOperation.Error(OperationError.Unauthorized)
        )

        assertThat(makeErrorRequest(404)).isEqualTo(
            ResultOfOperation.Error(OperationError.NotFound)
        )

        assertThat(makeErrorRequest(409)).isEqualTo(
            ResultOfOperation.Error(OperationError.Forbidden)
        )

        assertThat(makeErrorRequest(422)).isInstanceOf(
            ResultOfOperation.Error(OperationError.Validation(""))::class.java
        )

    }

    @AfterEach
    fun tearDown() {
        mockWebServer.shutdown()
    }
}