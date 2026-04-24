package com.example.domain

import com.example.domain.repository.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetUserProfileUseCase @Inject constructor(
    private val repo: UserRepository
) {
    suspend operator fun invoke(userId: Int) {
        withContext(Dispatchers.IO) {
            repo.showProfile(userId)
        }
    }
}