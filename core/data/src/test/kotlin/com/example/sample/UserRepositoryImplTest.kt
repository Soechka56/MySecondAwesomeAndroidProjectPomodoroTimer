import com.example.data.mapper.UserDataMapper
import com.example.data.repository.UserRepositoryImpl
import com.example.domain.repository.UserRepository
import com.example.network.PomodoroApi
import com.example.network.mapper.NetworkErrorMapper
import com.google.common.truth.Truth.assertThat
import com.google.gson.Gson
import kotlinx.coroutines.test.runTest
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.jupiter.api.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create


class UserRepositoryImplTest{
    private lateinit var mockWebServer: MockWebServer
    private lateinit var userRepository: UserRepository

    @Before fun setUp(){
        mockWebServer = MockWebServer()

        val retrofit = Retrofit.Builder()
            .baseUrl(mockWebServer.url("/"))
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        userRepository = UserRepositoryImpl(
            retrofit.create(PomodoroApi::class.java),
            userDataMapper = UserDataMapper(),
            networkErrorMapper = NetworkErrorMapper(gson = Gson()),
        )
    }

    @Test
    fun `getUser should parse network response and send correct model`() = runTest{
        val expectedJson = """
            {
              "id": 0,
              "username": "test_user",
              "full_name": "Test User",
              "avatar_url": null,
              "created_at": "...",
              "updated_at": "..."
            }
        """.trimIndent()

        val result = userRepository.showProfile(0)

        assertThat(result).isEqualTo(expectedJson)
    }

    @After fun tearDown() {
        mockWebServer.shutdown()
    }
}