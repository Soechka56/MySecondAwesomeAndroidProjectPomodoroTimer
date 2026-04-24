package com.example.domain

import com.example.domain.model.UserInfo
import com.example.domain.repository.UserRepository
import com.google.common.truth.Truth.assertThat
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Test


class GetUserProfileUseCaseTest {
    private val repo = mockk<UserRepository>()
    private val useCase = GetUserProfileUseCase(repo)

    // TODO("test delegation.. add domain-logic and test it!")
    @Test
    fun `invoke should return user from repo`() = runTest{
        val userExpected = UserInfo(
            id = 1,
            username = "Kamiliano",
            fullName = "Hasaniano",
            avatarUrl = "https://example.com/avatar.png",
            email = "john.archibald.campbell@example-pet-store.com",
            createdAt = "",
            updatedAt = ""
        )
        coEvery { repo.showProfile(1) } returns userExpected

        val result = useCase(1)

        assertThat(result).isEqualTo(userExpected)
        coVerify(exactly = 1) { repo.showProfile(1) }
    }
}