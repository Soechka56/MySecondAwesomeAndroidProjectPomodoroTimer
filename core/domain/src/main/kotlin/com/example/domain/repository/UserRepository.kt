package com.example.domain.repository

import com.example.domain.model.LoginInfo
import com.example.domain.model.UserInfo

interface UserRepository {
    suspend fun logInAccount(
        email: String,
        password: String,
    ): ResultOfOperation<LoginInfo>

    suspend fun signUpAccount(
        email: String,
        username: String,
        fullName: String,
        password: String,
    ): ResultOfOperation<LoginInfo>

    suspend fun showProfile(id: Int): ResultOfOperation<UserInfo>
}
