package com.example.network.mapper

import com.example.domain.repository.OperationError
import com.example.network.models.response.ErrorResponse
import com.google.gson.Gson
import okhttp3.ResponseBody
import javax.inject.Inject
import javax.inject.Singleton

sealed interface SimpleNetworkResult {
    data object Success : SimpleNetworkResult
    data class Error(val message: String) : SimpleNetworkResult
}

@Singleton
class NetworkErrorMapper @Inject constructor(
    private val gson: Gson,
) {
    fun map(code: Int, errorBody: ResponseBody? = null): SimpleNetworkResult {
        return when (code) {
            200 -> SimpleNetworkResult.Success
            201 -> SimpleNetworkResult.Success

            401 -> SimpleNetworkResult.Error(OperationError.Unauthorized.toString())
            404 -> SimpleNetworkResult.Error(OperationError.NotFound.toString())
            409 -> SimpleNetworkResult.Error("user with this email exist")
            422 -> SimpleNetworkResult.Error(parseValidationMessage(errorBody))

            else -> SimpleNetworkResult.Error("Unknown error")
        }
    }

    private fun parseValidationMessage(errorBody: ResponseBody?): String {
        if (errorBody == null) {
            return "Validation error"
        }

        return runCatching {
            gson.fromJson(errorBody.charStream(), ErrorResponse::class.java)
        }.getOrNull()
            ?.detail
            ?.joinToString(separator = ", ") { detail -> detail.msg }
            .orEmpty()
            .ifBlank { "Validation error" }
    }
}
