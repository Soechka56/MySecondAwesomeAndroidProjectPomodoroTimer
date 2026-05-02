package com.example.impl.model

import androidx.compose.runtime.Stable
import com.example.api.AuthType

@Stable
data class AuthScreenState (
    val authType: AuthType = AuthType.LOGIN,
    val email: String = "",
    val password: String = "",
    val username: String = "",
    val fullName: String = "",
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
    val loginResult: AuthResult? = null,
    val registrationResult: AuthResult? = null,
)