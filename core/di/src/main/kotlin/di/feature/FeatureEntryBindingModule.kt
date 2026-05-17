package com.example.di.feature

import com.example.impl.di.AuthEntryModule
import com.example.pomodorotimer.impl.di.PomodoroTimerEntryModule
import com.example.studysession.impl.di.StudySessionEntryModule
import dagger.Module

@Module(
    includes = [
        AuthEntryModule::class,
        PomodoroTimerEntryModule::class,
        StudySessionEntryModule::class
    ]
)
interface FeatureEntryBindingModule