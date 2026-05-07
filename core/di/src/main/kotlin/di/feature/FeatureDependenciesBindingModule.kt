package com.example.di.feature

import com.example.di.feature.binding.AuthFeatureBindingModule
import com.example.di.feature.binding.PomodoroTimerBindingModule
import dagger.Module

@Module(
    includes = [
        AuthFeatureBindingModule::class,
        PomodoroTimerBindingModule::class
    ]
)
interface FeatureDependenciesBindingModule