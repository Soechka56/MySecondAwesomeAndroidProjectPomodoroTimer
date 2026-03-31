package com.example.impl

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import com.example.api.contract.AuthResult
import com.example.api.contract.AuthType
import com.soechka1.designsystem.component.shared.BaseCard
import com.soechka1.designsystem.theme.PomodoroTheme

@Composable
fun AuthScreen(
    viewModel: AuthViewModel,
    onResult: (AuthResult) -> Unit,
    modifier: Modifier = Modifier,
) {
    val spacing = PomodoroTheme.spacing

    viewModel.resultEvent?.let { event ->
        LaunchedEffect(event.first) {
            onResult(event.second)
            viewModel.clearResultEvent()
        }
    }

    Scaffold(modifier = modifier) { paddingValues ->
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(spacing.medium),
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(spacing.large),
        ) {
            item {
                Text(
                    text = if (viewModel.authType == AuthType.LOGIN) "Вход" else "Регистрация",
                    style = MaterialTheme.typography.headlineMedium,
                )
            }

            item {
                BaseCard(
                    modifier = Modifier.fillMaxWidth(),
                    contentPadding = PaddingValues(spacing.large),
                ) {
                    Column(
                        verticalArrangement = Arrangement.spacedBy(spacing.medium),
                        modifier = Modifier.fillMaxWidth(),
                    ) {
                        Row(
                            horizontalArrangement = Arrangement.spacedBy(spacing.small),
                            modifier = Modifier.fillMaxWidth(),
                        ) {
                            Button(
                                onClick = { viewModel.updateAuthType(AuthType.LOGIN) },
                                modifier = Modifier.weight(1f),
                            ) {
                                Text(text = "Вход")
                            }
                            Button(
                                onClick = { viewModel.updateAuthType(AuthType.REGISTRATION) },
                                modifier = Modifier.weight(1f),
                            ) {
                                Text(text = "Регистрация")
                            }
                        }

                        OutlinedTextField(
                            value = viewModel.email,
                            onValueChange = viewModel::updateEmail,
                            label = { Text(text = "Email") },
                            singleLine = true,
                            modifier = Modifier.fillMaxWidth(),
                        )

                        if (viewModel.authType == AuthType.REGISTRATION) {
                            OutlinedTextField(
                                value = viewModel.username,
                                onValueChange = viewModel::updateUsername,
                                label = { Text(text = "Username") },
                                singleLine = true,
                                modifier = Modifier.fillMaxWidth(),
                            )

                            OutlinedTextField(
                                value = viewModel.fullName,
                                onValueChange = viewModel::updateFullName,
                                label = { Text(text = "Имя") },
                                singleLine = true,
                                modifier = Modifier.fillMaxWidth(),
                            )
                        }

                        OutlinedTextField(
                            value = viewModel.password,
                            onValueChange = viewModel::updatePassword,
                            label = { Text(text = "Пароль") },
                            visualTransformation = PasswordVisualTransformation(),
                            singleLine = true,
                            modifier = Modifier.fillMaxWidth(),
                        )

                        Button(
                            onClick = viewModel::submit,
                            enabled = !viewModel.isLoading,
                            modifier = Modifier.fillMaxWidth(),
                        ) {
                            if (viewModel.isLoading) {
                                CircularProgressIndicator()
                            } else {
                                Text(
                                    text = if (viewModel.authType == AuthType.LOGIN) {
                                        "Войти"
                                    } else {
                                        "Зарегистрироваться"
                                    }
                                )
                            }
                        }

                        TextButton(
                            onClick = {
                                viewModel.updateAuthType(
                                    if (viewModel.authType == AuthType.LOGIN) {
                                        AuthType.REGISTRATION
                                    } else {
                                        AuthType.LOGIN
                                    }
                                )
                            },
                            modifier = Modifier.fillMaxWidth(),
                        ) {
                            Text(
                                text = if (viewModel.authType == AuthType.LOGIN) {
                                    "Нет аккаунта? Создать"
                                } else {
                                    "Уже есть аккаунт? Войти"
                                }
                            )
                        }
                    }
                }
            }

            viewModel.errorMessage?.let { message ->
                item {
                    Text(
                        text = message,
                        color = MaterialTheme.colorScheme.error,
                    )
                }
            }

            viewModel.loginResult?.let { result ->
                item {
                    BaseCard(
                        modifier = Modifier.fillMaxWidth(),
                        contentPadding = PaddingValues(spacing.large),
                    ) {
                        Column(
                            verticalArrangement = Arrangement.spacedBy(spacing.small),
                            modifier = Modifier.fillMaxWidth(),
                        ) {
                            Text(text = "Результат входа")
                            Text(text = "token: ${result.accessToken}")
                            Text(text = "type: ${result.tokenType}")
                        }
                    }
                }
            }

            viewModel.registrationResult?.let { result ->
                item {
                    BaseCard(
                        modifier = Modifier.fillMaxWidth(),
                        contentPadding = PaddingValues(spacing.large),
                    ) {
                        Column(
                            verticalArrangement = Arrangement.spacedBy(spacing.small),
                            modifier = Modifier.fillMaxWidth(),
                        ) {
                            Text(text = "Результат регистрации")
                            Text(text = "id: ${result.id}")
                            Text(text = "username: ${result.username}")
                            Text(text = "name: ${result.fullName}")
                            Text(text = "email: ${result.email}")
                        }
                    }
                }
            }
        }
    }
}
