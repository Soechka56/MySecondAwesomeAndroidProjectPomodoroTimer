package com.example.di

import androidx.lifecycle.ViewModelProvider
import com.example.di.vmfactory.MultibindingViewModelFactory
import dagger.Binds
import dagger.Module

@Module
interface ViewModelFactoryModule {

    @Binds
    fun bindViewModelFactory(
        factory: MultibindingViewModelFactory
    ): ViewModelProvider.Factory
}