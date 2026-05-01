package com.example.sample

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.core.DataStoreFactory
import androidx.datastore.dataStoreFile
import androidx.test.core.app.ApplicationProvider
import com.example.data.datastore.UserPreferences
import com.example.data.datastore.UserPreferencesSerializer
import com.example.data.mapper.UserDataMapper
import com.example.data.repository.UserRepositoryImpl
import com.example.domain.repository.UserRepository
import com.example.network.PomodoroApi
import com.example.network.models.request.PostUserLoginData
import com.example.network.models.response.SuccessAuthResponse
import com.example.utils.CryptoManager
import com.google.common.truth.Truth.assertThat
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import retrofit2.Response

private const val testEmail = "test_email"
private const val testPassword = "test_password"
private const val testToken = "GRWG243FEF243RFDS423F4F32FSD4"
private const val testSharPrefName = "test.json"

class FakeCrypto : CryptoManager {
    private val prefix = "encrypted_".toByteArray()

    override fun encrypt(bytes: ByteArray): ByteArray {
        return prefix + bytes
    }
    override fun decrypt(bytes: ByteArray): ByteArray {
        return bytes.drop(prefix.size).toByteArray()
    }
}

@RunWith(RobolectricTestRunner::class)
class DataStoreTest {

    private lateinit var userRepository: UserRepository
    private lateinit var pomodoroApi: PomodoroApi
    private lateinit var dataStore: DataStore<UserPreferences>
    private lateinit var testContext: Context


    @Before
    fun setUp() {
        testContext = ApplicationProvider.getApplicationContext()
        dataStore = DataStoreFactory.create(
            serializer = UserPreferencesSerializer(FakeCrypto()),
            produceFile = {
                testContext.dataStoreFile(testSharPrefName)
            }
        )
        pomodoroApi = mockk()

        userRepository = UserRepositoryImpl(
            pomodoroApi = pomodoroApi,
            userDataMapper = UserDataMapper(),
            dataStore = dataStore,
            dispatcher = Dispatchers.IO
        )
    }

    @Test
    fun user_repo_should_work_with_data_store_correctly() = runTest {
        coEvery {
            pomodoroApi.loginAccount(PostUserLoginData(testEmail, testPassword))
        } returns Response.success(SuccessAuthResponse(testToken, ""))

        userRepository.logInAccount(testEmail, testPassword)
        val token = dataStore.data.first().token


        assertThat(token).isEqualTo(testToken)
    }

    @After
    fun clear() = runTest {
        dataStore.updateData { UserPreferences(token = "") }
    }
}