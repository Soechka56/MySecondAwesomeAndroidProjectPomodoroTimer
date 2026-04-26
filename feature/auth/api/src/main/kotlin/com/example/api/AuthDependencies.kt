package com.example.api

import android.content.Context
import com.example.domain.LogInUseCase
import com.example.domain.SignInUseCase
import com.example.navigation.Navigator

interface AuthDependencies {
    fun logInUseCase(): LogInUseCase
    fun signInUseCase(): SignInUseCase
    fun navigator(): Navigator
    fun context(): Context
}