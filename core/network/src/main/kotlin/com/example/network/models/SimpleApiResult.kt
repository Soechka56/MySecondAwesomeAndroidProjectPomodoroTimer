package com.example.network.models

sealed interface SimpleApiResult {
    data object Success : SimpleApiResult
    data class Error(val message: String) : SimpleApiResult
}