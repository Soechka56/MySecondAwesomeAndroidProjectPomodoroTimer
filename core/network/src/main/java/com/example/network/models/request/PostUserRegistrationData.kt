package com.example.network.models.request

import com.google.gson.annotations.SerializedName

data class PostUserRegistrationData(
    @SerializedName("email")
    val email: String,
    @SerializedName("username")
    val username: String,
    @SerializedName("full_name")
    val fullName: String,
    @SerializedName("password")
    val password: String,
)