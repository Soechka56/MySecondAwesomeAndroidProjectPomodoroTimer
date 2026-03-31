package com.example.impl.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.api.contract.AuthResult
import com.example.impl.AuthScreen
import com.example.impl.AuthViewModel

class AuthEntryProvider {
    @Composable
    fun Content(
        factory: AuthViewModel.Factory,
        onResult: (AuthResult) -> Unit,
        modifier: Modifier = Modifier,
    ) {
        val viewModel: AuthViewModel = viewModel(
            factory = factory,
        )

        AuthScreen(
            viewModel = viewModel,
            onResult = onResult,
            modifier = modifier,
        )
    }
}
