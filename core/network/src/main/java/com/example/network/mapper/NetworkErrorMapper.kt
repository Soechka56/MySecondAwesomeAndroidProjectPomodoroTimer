package com.example.network.mapper

import com.example.network.models.response.ErrorResponse
import com.google.gson.Gson
import okhttp3.ResponseBody

sealed interface SimpleNetworkResult {
    data object Success : SimpleNetworkResult
    data class Error(val message: String) : SimpleNetworkResult
}

class NetworkErrorMapper(
    private val gson: Gson = Gson(),
) {
    fun map(code: Int, errorBody: ResponseBody? = null): SimpleNetworkResult {
        return when (code) {
            200 -> SimpleNetworkResult.Success
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
