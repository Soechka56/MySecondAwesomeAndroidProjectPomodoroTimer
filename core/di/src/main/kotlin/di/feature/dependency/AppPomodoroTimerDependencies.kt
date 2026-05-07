package com.example.di.feature.dependency

import android.content.Context
import com.example.common.qualifier.ApplicationContext
import com.example.pomodorotimer.api.PomodoroTimerDependencies
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AppPomodoroTimerDependencies @Inject constructor(
    @ApplicationContext private val context: Context
) : PomodoroTimerDependencies {
    override fun context(): Context = context
}