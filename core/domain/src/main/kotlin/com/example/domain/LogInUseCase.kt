package com.example.domain

import com.example.domain.model.LoginInfo
import com.example.domain.repository.ResultOfOperation
import com.example.domain.repository.UserRepository
import javax.inject.Inject

class LogInUseCase @Inject constructor(
    private val userRepository: UserRepository
) {
    suspend operator fun invoke(
        email: String,
        password: String,
    ): ResultOfOperation<LoginInfo> {
        return userRepository.logInAccount(
            email = email,
            password = password,
        )
    }
}
