package com.example.domain

import com.example.domain.model.LoginInfo
import com.example.domain.repository.ResultOfOperation
import com.example.domain.repository.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class LogInUseCase @Inject constructor(
    private val userRepository: UserRepository
) {
    suspend operator fun invoke(
        email: String,
        password: String,
    ): ResultOfOperation<LoginInfo> {
        return withContext(Dispatchers.IO) {
            userRepository.logInAccount(
                email = email,
                password = password,
            )
        }
    }
}
