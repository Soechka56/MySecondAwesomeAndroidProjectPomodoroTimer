package com.example.api.contract

sealed interface AuthResult {

    data class Success(
        val type: AuthType,
    ) : AuthResult

    data class Error(
        val message: String,
    ) : AuthResult

    data object Cancelled : AuthResult
}

enum class AuthType {
    LOGIN,
    REGISTRATION,
}