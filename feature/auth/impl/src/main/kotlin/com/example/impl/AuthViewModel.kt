package com.example.impl

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.api.AuthResult
import com.example.api.AuthType
import com.example.domain.LogInUseCase
import com.example.domain.SignInUseCase
import com.example.domain.model.LoginInfo
import com.example.domain.repository.OperationError
import com.example.domain.repository.ResultOfOperation
import com.example.impl.model.AuthScreenEvent
import com.example.impl.model.AuthScreenState
import com.example.impl.model.AuthResult as TokenResult
import com.feature.auth.impl.R
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

class AuthViewModel @Inject constructor(
    private val appContext: Context,
    private val logInUseCase: LogInUseCase,
    private val signInUseCase: SignInUseCase,
) : ViewModel() {

    private val _state = MutableStateFlow(AuthScreenState())
    val state = _state.asStateFlow()

    private val _events = MutableSharedFlow<AuthScreenEvent>()
    val events = _events.asSharedFlow()


    fun updateEmail(value: String) {
        _state.update { state -> state.copy(email = value) }
    }

    fun updatePassword(value: String) {
        _state.update { state -> state.copy(password = value) }
    }

    fun updateUsername(value: String) {
        _state.update { state -> state.copy(username = value) }
    }


    fun updateFullName(value: String) {
        _state.update { state -> state.copy(fullName = value) }
    }


    fun updateAuthType(value: AuthType) {
        _state.update {
            it.copy(
                authType = value,
                errorMessage = null,
                loginResult = null,
                registrationResult = null
            )
        }
    }

    fun submit() {
        val validationMessage = validate()
        if (validationMessage != null) {
            _state.update {
                it.copy(errorMessage = validationMessage)
            }
            _events.tryEmit(AuthScreenEvent.ShowAuthResult(AuthResult.Error(validationMessage)))

            return
        }

        viewModelScope.launch {
            _state.update {
                it.copy(
                    isLoading = true,
                    errorMessage = null
                )
            }

            when (state.value.authType) {
                AuthType.LOGIN -> handleLogin()
                AuthType.REGISTRATION -> handleRegistration()
            }

            _state.update {
                it.copy(
                    isLoading = false
                )
            }
        }
    }

    private suspend fun handleLogin() {
        val result = with(_state.value) {
            logInUseCase(
                email = email.trim(),
                password = password
            )
        }

        when (result) {
            is ResultOfOperation.Success -> {
                _state.update { state ->
                    state.copy(
                        loginResult = result.data.toUiModel(),
                        registrationResult = null
                    )
                }


                _events.emit(
                    AuthScreenEvent.ShowAuthResult(
                        AuthResult.Success(AuthType.LOGIN)
                    )
                )


            }

            is ResultOfOperation.Error -> {
                errorAuth(result)
            }
        }
    }

    private suspend fun handleRegistration() {
        val result = with(state.value) {
            signInUseCase(
                email = email.trim(),
                username = username.trim(),
                fullName = fullName.trim(),
                password = password,
            )
        }

        when (result) {
            is ResultOfOperation.Success -> {
                _state.update { state ->
                    state.copy(
                        registrationResult = result.data.toUiModel(),
                        loginResult = null
                    )
                }
                _events.emit(
                    AuthScreenEvent.ShowAuthResult(
                        AuthResult.Success(AuthType.REGISTRATION)
                    )

                )
            }

            is ResultOfOperation.Error -> {
                errorAuth(result)
            }
        }
    }

    private fun errorAuth(result: ResultOfOperation.Error) {
        val message = result.error.toMessage()

        _state.update {
            it.copy(
                errorMessage = message
            )
        }

        viewModelScope.launch {
            _events.emit(
                AuthScreenEvent.ShowAuthResult(
                    AuthResult.Error(message)
                )
            )
        }
    }

    private fun validate(): String? {
        with(_state.value) {
            if (email.isBlank()) {
                return appContext.getString(R.string.auth_error_enter_email)
            }
            if (password.isBlank() || password.length < 8 || !password.any { it.isDigit() }) {
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
    }

    private fun OperationError.toMessage(): String {
        return when (this) {
            OperationError.NoInternet -> appContext.getString(R.string.auth_error_no_internet)
            OperationError.Unauthorized -> appContext.getString(R.string.auth_error_unauthorized)
            OperationError.Forbidden -> appContext.getString(R.string.auth_error_forbidden)
            OperationError.NotFound -> appContext.getString(R.string.auth_error_not_found)
            is OperationError.Validation -> appContext.getString(R.string.auth_error_validation)
            is OperationError.Unknown -> message
                ?: appContext.getString(R.string.auth_error_unknown)
        }
    }

    private fun LoginInfo.toUiModel(): TokenResult {
        return TokenResult(
            accessToken = accessToken,
            tokenType = tokenType,
        )
    }

}
