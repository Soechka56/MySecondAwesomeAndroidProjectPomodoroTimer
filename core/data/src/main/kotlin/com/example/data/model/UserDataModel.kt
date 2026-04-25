package com.example.data.model

data class UserDataModel(
    val id: Long,
    val username: String,
    val fullName: String,
    val avatarUrl: String?,
    val email: String,
    val createdAt: String,
    val updatedAt: String,
)