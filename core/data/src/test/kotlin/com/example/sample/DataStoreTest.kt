package com.example.sample

import com.example.data.datastore.UserPreferencesSerializer
import com.example.domain.model.LoginInfo
import com.example.domain.repository.ResultOfOperation
import com.example.domain.repository.UserRepository
import com.example.utils.Crypto
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class DataStoreTest {

    private val testEmail = "test_email"
    private val testPassword = "test_password"
    private val testToken = "GRWG243FEF243RFDS423F4F32FSD4"
    private lateinit var userRepository: UserRepository
    private lateinit var userPreferencesSerializer: UserPreferencesSerializer

    @BeforeEach
    fun setUp() {
        userRepository = mockk<UserRepository>()
        userPreferencesSerializer = UserPreferencesSerializer(Crypto())
    }

    @Test
    fun datastore_should_save_and_encrypt_user_token() = runTest {
        coEvery {
            userRepository.logInAccount(
                testEmail,
                testPassword
            )
        } returns ResultOfOperation.Success(
            LoginInfo(
                accessToken = testToken, tokenType = ""
            )
        )

        val result = userRepository.logInAccount(testEmail, testPassword)
        val token = when (result) {
            is ResultOfOperation.Success -> result.data.accessToken
            else -> ""
        }



    }

}