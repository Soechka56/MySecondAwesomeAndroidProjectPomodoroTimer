package com.example.di

import com.example.domain.LogInUseCase
import com.example.domain.SignInUseCase
import com.example.impl.AuthViewModel
import dagger.Module
import dagger.Provides

@Module
class AuthModule {
    @Provides
    fun provideAuthViewModelFactory(
        logInUseCase: LogInUseCase,
        signInUseCase: SignInUseCase,
    ): AuthViewModel.Factory {
        return AuthViewModel.Factory(
            logInUseCase = logInUseCase,
            signInUseCase = signInUseCase,
        )
    }
}
