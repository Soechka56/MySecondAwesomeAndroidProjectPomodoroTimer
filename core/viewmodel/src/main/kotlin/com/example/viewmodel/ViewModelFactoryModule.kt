package com.example.viewmodel

import androidx.lifecycle.ViewModelProvider
import com.example.viewmodel.vmfactory.MultibindingViewModelFactory
import dagger.Binds
import dagger.Module

@Module
interface ViewModelFactoryModule {

    @Binds
    fun bindViewModelFactory(
        factory: MultibindingViewModelFactory
    ): ViewModelProvider.Factory
}