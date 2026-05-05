package com.example.di.feature

import com.example.impl.di.AuthEntryModule
import com.example.pomodorotimer.impl.di.PomodoroTimerEntryModule
import dagger.Module

@Module(
    includes = [
        AuthEntryModule::class,
        PomodoroTimerEntryModule::class
    ]
)
interface FeatureEntryBindingModule