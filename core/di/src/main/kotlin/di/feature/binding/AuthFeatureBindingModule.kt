package com.example.di.feature.binding

import com.example.api.AuthDependencies
import com.example.di.feature.dependency.AppAuthDependencies
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
interface AuthFeatureBindingModule {

    @Binds
    @Singleton
    fun bindAuthDependencies(
        impl: AppAuthDependencies,
    ): AuthDependencies
}