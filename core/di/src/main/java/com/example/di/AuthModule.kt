package com.example.di

import android.content.Context
import com.example.domain.LogInUseCase
import com.example.domain.SignInUseCase
import com.example.impl.AuthViewModel
import dagger.Module
import dagger.Provides

@Module
class AuthModule {
    @Provides
    fun provideAuthViewModelFactory(
        context: Context,
        logInUseCase: LogInUseCase,
        signInUseCase: SignInUseCase,
    ): AuthViewModel.Factory {
        return AuthViewModel.Factory(
            appContext = context.applicationContext,
            logInUseCase = logInUseCase,
            signInUseCase = signInUseCase,
        )
    }
}