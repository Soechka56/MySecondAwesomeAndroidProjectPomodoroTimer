package com.example.di.feature.dependency

import android.content.Context
import com.example.api.AuthDependencies
import com.example.common.qualifier.ApplicationContext
import com.example.domain.LogInUseCase
import com.example.domain.SignInUseCase
import com.example.navigation.Navigator
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AppAuthDependencies @Inject constructor(
    private val logInUseCase: LogInUseCase,
    private val signInUseCase: SignInUseCase,
    private val navigator: Navigator,
    @ApplicationContext private val context: Context,
) : AuthDependencies {

    override fun logInUseCase(): LogInUseCase = logInUseCase
    override fun signInUseCase(): SignInUseCase = signInUseCase
    override fun navigator(): Navigator = navigator
    override fun context(): Context = context
}