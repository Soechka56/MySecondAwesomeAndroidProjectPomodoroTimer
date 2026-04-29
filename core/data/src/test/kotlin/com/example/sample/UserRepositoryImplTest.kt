import com.example.data.mapper.UserDataMapper
import com.example.data.repository.UserRepositoryImpl
import com.example.domain.model.UserInfo
import com.example.domain.repository.OperationError
import com.example.domain.repository.ResultOfOperation
import com.example.domain.repository.UserRepository
import com.example.network.PomodoroApi
import com.google.common.truth.Truth.assertThat
import io.mockk.MockKAnnotations
import io.mockk.impl.annotations.AdditionalInterface
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.test.runTest
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Timeout
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

@Timeout(value = 15, unit = TimeUnit.SECONDS)
class UserRepositoryImplTest {
    private lateinit var mockWebServer: MockWebServer
    private lateinit var userRepository: UserRepository

    @BeforeEach
    fun setUp() {
        mockWebServer = MockWebServer()
        mockWebServer.start()

        MockKAnnotations.init()

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

    @ParameterizedTest(name = "code{0}, expectedCode{1}")
    @MethodSource("data")
    fun `errorMapper should return error when response code is not success`(
        code: Int, expectedError: OperationError
    ) = runTest {

        suspend fun makeErrorRequest(code: Int): ResultOfOperation<UserInfo> {
            mockWebServer.enqueue(
                MockResponse().setResponseCode(code)
            )
            return userRepository.showProfile(1)
        }

        assertThat(makeErrorRequest(code)).isEqualTo(
            ResultOfOperation.Error(expectedError)
        )

    }

    @AfterEach
    fun tearDown() {
        mockWebServer.shutdown()
    }

    companion object {
        @JvmStatic
        fun data() = listOf(
            arrayOf(401, OperationError.Unauthorized),
            arrayOf(404, OperationError.NotFound),
            arrayOf(409, OperationError.Forbidden),
        )
    }
}
