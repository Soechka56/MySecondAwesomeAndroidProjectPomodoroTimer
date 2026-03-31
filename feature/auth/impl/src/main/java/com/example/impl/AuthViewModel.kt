package com.example.impl

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import com.example.api.contract.AuthResult
import com.example.api.contract.AuthType
import com.example.api.contract.model.LoginResult
import com.example.api.contract.model.RegistrationResult
import com.example.domain.LogInUseCase
import com.example.domain.SignInUseCase
import com.example.domain.model.LoginInfo
import com.example.domain.model.UserInfo
import com.example.domain.repository.OperationError
import com.example.domain.repository.ResultOfOperation
import kotlinx.coroutines.launch
import kotlin.reflect.KClass

class AuthViewModel(
    private val logInUseCase: LogInUseCase,
    private val signInUseCase: SignInUseCase,
) : ViewModel() {
    var authType by mutableStateOf(AuthType.LOGIN)
        private set

    var email by mutableStateOf("")
        private set

    var password by mutableStateOf("")
        private set

    var username by mutableStateOf("")
        private set

    var fullName by mutableStateOf("")
        private set

    var isLoading by mutableStateOf(false)
        private set

    var errorMessage by mutableStateOf<String?>(null)
        private set

    var loginResult by mutableStateOf<LoginResult?>(null)
        private set

    var registrationResult by mutableStateOf<RegistrationResult?>(null)
        private set

    var resultEvent by mutableStateOf<Pair<Long, AuthResult>?>(null)
        private set

    fun updateEmail(value: String) {
        email = value
    }

    fun updatePassword(value: String) {
        password = value
    }

    fun updateUsername(value: String) {
        username = value
    }

    fun updateFullName(value: String) {
        fullName = value
    }

    fun updateAuthType(value: AuthType) {
        authType = value
        errorMessage = null
        loginResult = null
        registrationResult = null
    }

    fun clearResultEvent() {
        resultEvent = null
    }

    fun submit() {
        val validationMessage = validate()
        if (validationMessage != null) {
            errorMessage = validationMessage
            resultEvent = System.currentTimeMillis() to AuthResult.Error(validationMessage)
            return
        }

        viewModelScope.launch {
            isLoading = true
            errorMessage = null

            when (authType) {
                AuthType.LOGIN -> handleLogin()
                AuthType.REGISTRATION -> handleRegistration()
            }

            isLoading = false
        }
    }

    private suspend fun handleLogin() {
        when (
            val result = logInUseCase(
                email = email.trim(),
                password = password,
            )
        ) {
            is ResultOfOperation.Success -> {
                loginResult = result.data.toUiModel()
                registrationResult = null
                resultEvent = System.currentTimeMillis() to AuthResult.Success(AuthType.LOGIN)
            }

            is ResultOfOperation.Error -> {
                val message = result.error.toMessage()
                errorMessage = message
                resultEvent = System.currentTimeMillis() to AuthResult.Error(message)
            }
        }
    }

    private suspend fun handleRegistration() {
        when (
            val result = signInUseCase(
                email = email.trim(),
                username = username.trim(),
                fullName = fullName.trim(),
                password = password,
            )
        ) {
            is ResultOfOperation.Success -> {
                registrationResult = result.data.toUiModel()
                loginResult = null
                resultEvent = System.currentTimeMillis() to AuthResult.Success(AuthType.REGISTRATION)
            }

            is ResultOfOperation.Error -> {
                val message = result.error.toMessage()
                errorMessage = message
                resultEvent = System.currentTimeMillis() to AuthResult.Error(message)
            }
        }
    }

    private fun validate(): String? {
        if (email.isBlank()) {
            return "Введите email"
        }
        if (password.isBlank()) {
            return "Введите пароль"
        }
        if (authType == AuthType.REGISTRATION && username.isBlank()) {
            return "Введите username"
        }
        if (authType == AuthType.REGISTRATION && fullName.isBlank()) {
            return "Введите имя"
        }
        return null
    }

    private fun OperationError.toMessage(): String {
        return when (this) {
            OperationError.NoInternet -> "Нет интернета"
            OperationError.Unauthorized -> "Ошибка авторизации"
            OperationError.Forbidden -> "Доступ запрещен"
            OperationError.NotFound -> "Ничего не найдено"
            is OperationError.Validation -> message
            is OperationError.Unknown -> message ?: "Неизвестная ошибка"
        }
    }

    private fun LoginInfo.toUiModel(): LoginResult {
        return LoginResult(
            accessToken = accessToken,
            tokenType = tokenType,
        )
    }

    private fun UserInfo.toUiModel(): RegistrationResult {
        return RegistrationResult(
            id = id,
            username = username,
            fullName = fullName,
            avatarUrl = avatarUrl,
            email = email,
            createdAt = createdAt,
            updatedAt = updatedAt,
        )
    }

    class Factory(
        private val logInUseCase: LogInUseCase,
        private val signInUseCase: SignInUseCase,
    ) : ViewModelProvider.Factory {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(
            modelClass: KClass<T>,
            extras: CreationExtras
        ): T {
            return AuthViewModel(
                logInUseCase = logInUseCase,
                signInUseCase = signInUseCase,
            ) as T
        }
    }
}
