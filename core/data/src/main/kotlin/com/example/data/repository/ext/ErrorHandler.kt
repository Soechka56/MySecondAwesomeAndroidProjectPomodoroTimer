package com.example.data.repository.ext

import com.example.domain.repository.OperationError
import com.example.domain.repository.ResultOfOperation
import retrofit2.HttpException
import java.io.IOException

fun <T> Throwable.toResultOfOperation(): ResultOfOperation<T> {
    return when (this) {
        is IOException -> {
            ResultOfOperation.Error(OperationError.NoInternet)
        }

        is HttpException -> {
            when (code()) {
                401 -> ResultOfOperation.Error(OperationError.Unauthorized)
                404 -> ResultOfOperation.Error(OperationError.NotFound)
                409 -> ResultOfOperation.Error(OperationError.Forbidden)
                422 -> ResultOfOperation.Error(OperationError.Validation(message()))
                else -> ResultOfOperation.Error(
                    OperationError.Unknown(message())
                )
            }
        }

        else -> {
            ResultOfOperation.Error(
                OperationError.Unknown(message ?: "Unknown error")
            )
        }
    }
}
