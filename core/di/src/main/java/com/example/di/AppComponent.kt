package com.example.di

import com.example.impl.AuthViewModel
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        NetworkModule::class,
        DataModule::class,
        DomainModule::class,
        AuthModule::class,
    ]
)
interface AppComponent {
    fun authViewModelFactory(): AuthViewModel.Factory
}
