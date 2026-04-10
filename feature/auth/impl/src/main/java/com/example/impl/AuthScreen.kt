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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.api.AuthResult
import com.example.api.AuthType
import com.soechka1.designsystem.component.shared.BaseCard
import com.soechka1.designsystem.theme.PomodoroTheme

@Composable
fun AuthScreen(
    factory: AuthViewModel.Factory,
    onResult: (AuthResult) -> Unit,
    modifier: Modifier = Modifier,
) {
    val spacing = PomodoroTheme.spacing
    val viewModel: AuthViewModel = viewModel(factory = factory)

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
                    text = if (viewModel.authType == AuthType.LOGIN) {
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
                        Row(
                            horizontalArrangement = Arrangement.spacedBy(spacing.small),
                            modifier = Modifier.fillMaxWidth(),
                        ) {
                            Button(
                                onClick = { viewModel.updateAuthType(AuthType.LOGIN) },
                                modifier = Modifier.weight(1f),
                            ) {
                                Text(text = stringResource(R.string.auth_tab_login))
                            }
                            Button(
                                onClick = { viewModel.updateAuthType(AuthType.REGISTRATION) },
                                modifier = Modifier.weight(1f),
                            ) {
                                Text(text = stringResource(R.string.auth_tab_registration))
                            }
                        }

                        OutlinedTextField(
                            value = viewModel.email,
                            onValueChange = viewModel::updateEmail,
                            label = { Text(text = stringResource(R.string.auth_label_email)) },
                            singleLine = true,
                            modifier = Modifier.fillMaxWidth(),
                        )

                        if (viewModel.authType == AuthType.REGISTRATION) {
                            OutlinedTextField(
                                value = viewModel.username,
                                onValueChange = viewModel::updateUsername,
                                label = { Text(text = stringResource(R.string.auth_label_username)) },
                                singleLine = true,
                                modifier = Modifier.fillMaxWidth(),
                            )

                            OutlinedTextField(
                                value = viewModel.fullName,
                                onValueChange = viewModel::updateFullName,
                                label = { Text(text = stringResource(R.string.auth_label_name)) },
                                singleLine = true,
                                modifier = Modifier.fillMaxWidth(),
                            )
                        }

                        OutlinedTextField(
                            value = viewModel.password,
                            onValueChange = viewModel::updatePassword,
                            label = { Text(text = stringResource(R.string.auth_label_password)) },
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
                                    stringResource(R.string.auth_switch_to_registration)
                                } else {
                                    stringResource(R.string.auth_switch_to_login)
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
                            Text(text = stringResource(R.string.auth_result_login_title))
                            Text(text = stringResource(R.string.auth_result_token, result.accessToken))
                            Text(text = stringResource(R.string.auth_result_type, result.tokenType))
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
                            Text(text = stringResource(R.string.auth_result_registration_title))
                            Text(text = stringResource(R.string.auth_result_id, result.id))
                            Text(text = stringResource(R.string.auth_result_username, result.username))
                            Text(text = stringResource(R.string.auth_result_name, result.fullName))
                            Text(text = stringResource(R.string.auth_result_email, result.email))
                        }
                    }
                }
            }
        }
    }
}