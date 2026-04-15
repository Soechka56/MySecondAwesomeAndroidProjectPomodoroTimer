package com.example.impl

import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import com.example.api.AuthResult
import com.example.api.AuthType
import com.example.impl.model.LoginResult
import com.example.impl.model.RegistrationResult
import com.example.domain.LogInUseCase
import com.example.domain.SignInUseCase
import com.example.domain.model.LoginInfo
import com.example.domain.model.UserInfo
import com.example.domain.repository.OperationError
import com.example.domain.repository.ResultOfOperation
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.reflect.KClass

class AuthViewModel @Inject constructor(
    private val appContext: Context,
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
            return appContext.getString(R.string.auth_error_enter_email)
        }
        if (password.isBlank()) {
            return appContext.getString(R.string.auth_error_enter_password)
        }
        if (authType == AuthType.REGISTRATION && username.isBlank()) {
            return appContext.getString(R.string.auth_error_enter_username)
        }
        if (authType == AuthType.REGISTRATION && fullName.isBlank()) {
            return appContext.getString(R.string.auth_error_enter_name)
        }
        return null
    }

    private fun OperationError.toMessage(): String {
        return when (this) {
            OperationError.NoInternet -> appContext.getString(R.string.auth_error_no_internet)
            OperationError.Unauthorized -> appContext.getString(R.string.auth_error_unauthorized)
            OperationError.Forbidden -> appContext.getString(R.string.auth_error_forbidden)
            OperationError.NotFound -> appContext.getString(R.string.auth_error_not_found)
            is OperationError.Validation -> message
            is OperationError.Unknown -> message ?: appContext.getString(R.string.auth_error_unknown)
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

}
