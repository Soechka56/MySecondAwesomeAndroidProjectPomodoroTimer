package com.example.di.feature.binding

import com.example.di.feature.dependency.AppPomodoroTimerDependencies
import com.example.pomodorotimer.api.PomodoroTimerDependencies
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
interface PomodoroTimerBindingModule {
    @Binds
    @Singleton
    fun bindPomodoroTimerDependencies(
        impl: AppPomodoroTimerDependencies
    ): PomodoroTimerDependencies
}