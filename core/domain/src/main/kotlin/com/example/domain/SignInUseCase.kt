package com.example.domain

import com.example.domain.model.UserInfo
import com.example.domain.repository.ResultOfOperation
import com.example.domain.repository.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class SignInUseCase @Inject constructor(
    private val userRepository: UserRepository
) {
    suspend operator fun invoke(
        email: String,
        username: String,
        fullName: String,
        password: String,
    ): ResultOfOperation<UserInfo> {
        return withContext(Dispatchers.IO) {
            userRepository.signUpAccount(
                email = email,
                username = username,
                fullName = fullName,
                password = password,
            )
        }
    }
}
