package com.example.domain

import com.example.domain.model.LoginInfo
import com.example.domain.repository.ResultOfOperation
import com.example.domain.repository.UserRepository
import javax.inject.Inject

class SignInUseCase @Inject constructor(
    private val userRepository: UserRepository
) {
    suspend operator fun invoke(
        email: String,
        username: String,
        fullName: String,
        password: String,
    ): ResultOfOperation<LoginInfo> {
        return userRepository.signUpAccount(
            email = email,
            username = username,
            fullName = fullName,
            password = password,
        )
    }
}
