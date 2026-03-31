package com.example.network.models.request

data class PostUserRegistrationData(
    val email: String,
    val username: String,
    val fullName: String,
    val password: String,
)