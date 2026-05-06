package com.example.pomodorotimer.impl.model

import androidx.compose.runtime.Immutable

// test data
@Immutable
data class PomodoroTimerState(
    val subjects: List<PomodoroSubjectUiModel> = defaultSubjects,
    val goalProgress: Float = 0.38f,
    val goalText: String = "3/5h",
    val isAddSubjectDialogShown: Boolean = false,
    val newSubjectName: String = "",
    val selectedIcon: SubjectIcon = SubjectIcon.Trend,
)

// test data
data class PomodoroSubjectUiModel(
    val title: String,
    val time: String = "00:00:00",
    val icon: SubjectIcon = SubjectIcon.Trend,
    val color: SubjectColor = SubjectColor.Blue,
    val isWide: Boolean = false,
)


private val defaultSubjects = listOf(
    PomodoroSubjectUiModel(
        title = "Vibe Coding",
        color = SubjectColor.Blue,
        icon = SubjectIcon.Trend,
        isWide = true,
    ),
    PomodoroSubjectUiModel(
        title = "Math",
        color = SubjectColor.Purple,
        icon = SubjectIcon.Trend,
    ),
    PomodoroSubjectUiModel(
        title = "Math",
        color = SubjectColor.Green,
        icon = SubjectIcon.Trend,
    ),
    PomodoroSubjectUiModel(
        title = "Vibe Coding",
        color = SubjectColor.Orange,
        icon = SubjectIcon.Spark,
        isWide = true,
    ),
    PomodoroSubjectUiModel(
        title = "Math",
        color = SubjectColor.Green,
        icon = SubjectIcon.Trend,
    ),
    PomodoroSubjectUiModel(
        title = "Math",
        color = SubjectColor.Yellow,
        icon = SubjectIcon.Trend,
    ),
)
