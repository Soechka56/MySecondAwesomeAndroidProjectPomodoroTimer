package com.example.domain.repository

import com.example.domain.model.UserInfo

interface UserRepository {
    suspend fun logInAccount(): ResultOfOperation<UserInfo>
    suspend fun signUpAccount(): ResultOfOperation<UserInfo>
}

