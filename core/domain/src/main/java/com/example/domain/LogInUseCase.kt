package com.example.domain

import com.example.domain.model.UserInfo
import com.example.domain.repository.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class LogInUseCase(
    private val userRepository: UserRepository
) {
    suspend operator fun invoke(): UserInfo {
        return withContext(Dispatchers.IO){
            userRepository.logInAccount()
        }
    }
}