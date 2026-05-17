package com.example.di.feature

import com.example.api.AuthDependencies
import com.example.di.feature.dependency.AppAuthDependencies
import com.example.di.feature.dependency.AppPomodoroTimerDependencies
import com.example.di.feature.dependency.AppStudySessionDependencies
import com.example.pomodorotimer.api.PomodoroTimerDependencies
import com.example.studysession.api.StudySessionDependencies
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
interface FeatureDependenciesBindingModule {
    @Binds
    @Singleton
    fun bindAuthDependencies(
        impl: AppAuthDependencies
    ): AuthDependencies

    @Binds
    @Singleton
    fun bindPomodoroTimerDependencies(
        impl: AppPomodoroTimerDependencies
    ): PomodoroTimerDependencies

    @Binds
    @Singleton
    fun bindStudySessionDependencies(
        impl: AppStudySessionDependencies
    ): StudySessionDependencies
}