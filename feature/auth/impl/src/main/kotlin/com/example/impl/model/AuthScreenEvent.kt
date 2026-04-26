package com.example.impl.model

import com.example.api.AuthResult

sealed class AuthScreenEvent {
    data class ShowAuthResult(val result: AuthResult) : AuthScreenEvent()
    data object NavigateNext: AuthScreenEvent()
}