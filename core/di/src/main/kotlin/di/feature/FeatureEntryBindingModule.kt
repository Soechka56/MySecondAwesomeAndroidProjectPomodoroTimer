package com.example.di.feature

import com.example.impl.di.AuthEntryModule
import dagger.Module

@Module(
    includes = [
        AuthEntryModule::class,
    ]
)
interface FeatureEntryBindingModule