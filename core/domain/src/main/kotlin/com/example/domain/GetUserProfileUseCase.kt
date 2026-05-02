package com.example.domain

import com.example.domain.repository.UserRepository
import javax.inject.Inject

class GetUserProfileUseCase @Inject constructor(
    private val repo: UserRepository
) {
    suspend operator fun invoke(userId: Int) = repo.showProfile(userId)
}