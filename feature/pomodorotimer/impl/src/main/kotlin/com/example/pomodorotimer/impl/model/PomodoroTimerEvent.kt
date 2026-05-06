package com.example.pomodorotimer.impl.model

sealed interface PomodoroTimerEvent {
    data object StartStudyRequested : PomodoroTimerEvent
}
