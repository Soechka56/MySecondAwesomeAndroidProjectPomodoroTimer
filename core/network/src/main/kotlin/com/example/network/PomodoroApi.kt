package com.example.network

import com.example.network.models.request.PostUserLoginData
import com.example.network.models.request.PostUserRegistrationData
import com.example.network.models.response.SuccessAuthResponse
import com.example.network.models.response.UserInfoResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface PomodoroApi {
    @POST("auth/register")
    suspend fun registerAccount(
        @Body user: PostUserRegistrationData
    ): Response<SuccessAuthResponse>

    @POST("auth/login")
    suspend fun loginAccount(
        @Body user: PostUserLoginData
    ): Response<SuccessAuthResponse>

    @GET("users/{id}")
    suspend fun getUserProfile(
        @Path("id") id: Int
    ): Response<UserInfoResponse>

}
