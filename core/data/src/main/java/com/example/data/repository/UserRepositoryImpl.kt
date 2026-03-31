package com.example.data.repository

import com.example.domain.model.UserInfo
import com.example.domain.repository.ResultOfOperation
import com.example.domain.repository.UserRepository

class UserRepositoryImpl: UserRepository{
    override suspend fun logInAccount(): ResultOfOperation<UserInfo> {
        TODO("Not yet implemented")
    }

    override suspend fun signUpAccount(): ResultOfOperation<UserInfo> {
        TODO("Not yet implemented")
    }

}