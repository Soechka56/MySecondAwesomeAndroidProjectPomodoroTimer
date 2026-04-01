package com.example.di

import com.example.domain.LogInUseCase
import com.example.domain.SignInUseCase
import com.example.domain.repository.UserRepository
import dagger.Module
import dagger.Provides

@Module
class DomainModule {
    @Provides
    fun provideLogInUseCase(
        userRepository: UserRepository,
    ): LogInUseCase {
        return LogInUseCase(userRepository)
    }

    @Provides
    fun provideSignInUseCase(
        userRepository: UserRepository,
    ): SignInUseCase {
        return SignInUseCase(userRepository)
    }
}
