package com.example.di.feature

import com.example.di.feature.binding.AuthFeatureBindingModule
import dagger.Module

@Module(
    includes = [
        AuthFeatureBindingModule::class
    ]
)
interface FeatureDependenciesBindingModule