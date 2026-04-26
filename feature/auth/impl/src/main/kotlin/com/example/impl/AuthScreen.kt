package com.example.impl

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
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
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.api.AuthResult
import com.example.api.AuthType
import com.example.impl.model.AuthScreenEvent
import com.feature.auth.impl.R
import com.soechka1.designsystem.component.shared.BaseCard
import com.soechka1.designsystem.theme.PomodoroTheme

@Composable
fun AuthScreen(
    viewModel: AuthViewModel,
    onResult: (AuthResult) -> Unit,
    modifier: Modifier = Modifier,
) {
    val spacing = PomodoroTheme.spacing
    val uiState by viewModel.state.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.events.collect { event ->
            when (event) {
                is AuthScreenEvent.ShowAuthResult -> {}
                is AuthScreenEvent.NavigateNext -> {}
            }
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
                    text = if (uiState.authType == AuthType.LOGIN) {
                        stringResource(R.string.auth_title_login)
                    } else {
                        stringResource(R.string.auth_title_registration)
                    },
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
                        OutlinedTextField(
                            value = uiState.email,
                            onValueChange = viewModel::updateEmail,
                            label = { Text(text = stringResource(R.string.auth_label_email)) },
                            singleLine = true,
                            modifier = Modifier.fillMaxWidth(),
                        )

                        if (uiState.authType == AuthType.REGISTRATION) {
                            OutlinedTextField(
                                value = uiState.username,
                                onValueChange = viewModel::updateUsername,
                                label = { Text(text = stringResource(R.string.auth_label_username)) },
                                singleLine = true,
                                modifier = Modifier.fillMaxWidth(),
                            )

                            OutlinedTextField(
                                value = uiState.fullName,
                                onValueChange = viewModel::updateFullName,
                                label = { Text(text = stringResource(R.string.auth_label_name)) },
                                singleLine = true,
                                modifier = Modifier.fillMaxWidth(),
                            )
                        }

                        OutlinedTextField(
                            value = uiState.password,
                            onValueChange = viewModel::updatePassword,
                            label = { Text(text = stringResource(R.string.auth_label_password)) },
                            visualTransformation = PasswordVisualTransformation(),
                            singleLine = true,
                            modifier = Modifier.fillMaxWidth(),
                        )

                        Button(
                            onClick = viewModel::submit,
                            enabled = !uiState.isLoading,
                            modifier = Modifier.fillMaxWidth(),
                        ) {
                            if (uiState.isLoading) {
                                CircularProgressIndicator()
                            } else {
                                Text(
                                    text = if (uiState.authType == AuthType.LOGIN) {
                                        stringResource(R.string.auth_action_login)
                                    } else {
                                        stringResource(R.string.auth_action_registration)
                                    }
                                )
                            }
                        }

                        TextButton(
                            onClick = {
                                viewModel.updateAuthType(
                                    if (uiState.authType == AuthType.LOGIN) {
                                        AuthType.REGISTRATION
                                    } else {
                                        AuthType.LOGIN
                                    }
                                )
                            },
                            modifier = Modifier.fillMaxWidth(),
                        ) {
                            Text(
                                text = if (uiState.authType == AuthType.LOGIN) {
                                    stringResource(R.string.auth_switch_to_registration)
                                } else {
                                    stringResource(R.string.auth_switch_to_login)
                                }
                            )
                        }
                    }
                }
            }

            uiState.errorMessage?.let { message ->
                item {
                    Text(
                        text = message,
                        color = MaterialTheme.colorScheme.error,
                    )
                }
            }

            uiState.loginResult?.let { result ->
                item {
                    BaseCard(
                        modifier = Modifier.fillMaxWidth(),
                        contentPadding = PaddingValues(spacing.large),
                    ) {
                        Column(
                            verticalArrangement = Arrangement.spacedBy(spacing.small),
                            modifier = Modifier.fillMaxWidth(),
                        ) {
                            Text(text = stringResource(R.string.auth_result_login_title))
                            Text(
                                text = stringResource(
                                    R.string.auth_result_token,
                                    result.accessToken
                                )
                            )
                            Text(text = stringResource(R.string.auth_result_type, result.tokenType))
                        }
                    }
                }
            }

            uiState.registrationResult?.let { result ->
                item {
                    BaseCard(
                        modifier = Modifier.fillMaxWidth(),
                        contentPadding = PaddingValues(spacing.large),
                    ) {
                        Column(
                            verticalArrangement = Arrangement.spacedBy(spacing.small),
                            modifier = Modifier.fillMaxWidth(),
                        ) {
                            Text(text = stringResource(R.string.auth_result_registration_title))
                            Text(text = stringResource(R.string.auth_result_id, result.id))
                            Text(
                                text = stringResource(
                                    R.string.auth_result_username,
                                    result.username
                                )
                            )
                            Text(text = stringResource(R.string.auth_result_name, result.fullName))
                            Text(text = stringResource(R.string.auth_result_email, result.email))
                        }
                    }
                }
            }
        }
    }
}