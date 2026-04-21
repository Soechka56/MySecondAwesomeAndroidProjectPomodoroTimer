package com.example.impl.model

import com.example.api.AuthType

data class AuthScreenState (
    var authType: AuthType = AuthType.LOGIN,
    var email: String = "",
    var password: String = "",
    var username: String= "",
    var fullName: String = "",
    var isLoading: Boolean = false,
    var errorMessage: String? = null,

    val loginResult: LoginResult? = null,
    val registrationResult: RegistrationResult? = null,
)