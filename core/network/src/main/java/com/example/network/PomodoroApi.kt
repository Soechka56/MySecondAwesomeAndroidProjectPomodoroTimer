package com.example.network

import com.example.network.models.request.PostUserLoginData
import com.example.network.models.request.PostUserRegistrationData
import com.example.network.models.response.SuccessLoginResponse
import com.example.network.models.response.SuccessRegistrationResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface PomodoroApi {
    @POST("auth/register")
    suspend fun registerAccount(
        @Body user: PostUserRegistrationData
    ): Response<SuccessRegistrationResponse>

    @POST("auth/login")
    suspend fun loginAccount(
        @Body user: PostUserLoginData
    ): Response<SuccessLoginResponse>

}
