package com.example.domain.repository

sealed interface ResultOfOperation<out T> {
    data class Success<T>(val data: T) : ResultOfOperation<T>
    data class Error(val error: OperationError) : ResultOfOperation<Nothing>
}

sealed interface OperationError {
    data object NoInternet : OperationError
    data object Unauthorized : OperationError
    data object Forbidden : OperationError
    data object NotFound : OperationError
    data class Validation(val message: String) : OperationError
    data class Unknown(val message: String? = null) : OperationError
}