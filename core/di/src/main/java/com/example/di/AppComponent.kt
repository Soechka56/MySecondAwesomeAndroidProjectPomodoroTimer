package com.example.di

import android.content.Context
import com.example.impl.AuthViewModel
import dagger.BindsInstance
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

    @Component.Factory
    interface Factory {
        fun create(
            @BindsInstance context: Context,
        ): AppComponent
    }
}