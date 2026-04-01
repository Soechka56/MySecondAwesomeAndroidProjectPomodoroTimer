package com.example.api.contract.model

data class RegistrationResult(
    val id: Long,
    val username: String,
    val fullName: String,
    val avatarUrl: String?,
    val email: String,
    val createdAt: String,
    val updatedAt: String,
)
