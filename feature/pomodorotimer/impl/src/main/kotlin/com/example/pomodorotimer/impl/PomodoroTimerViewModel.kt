package com.example.pomodorotimer.impl

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pomodorotimer.impl.model.PomodoroSubjectUiModel
import com.example.pomodorotimer.impl.model.PomodoroTimerEvent
import com.example.pomodorotimer.impl.model.PomodoroTimerState
import com.example.pomodorotimer.impl.model.SubjectColor
import com.example.pomodorotimer.impl.model.SubjectIcon
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

class PomodoroTimerViewModel @Inject constructor() : ViewModel() {

    private val _state = MutableStateFlow(PomodoroTimerState())
    val state = _state.asStateFlow()

    private val _events = MutableSharedFlow<PomodoroTimerEvent>()
    val events = _events.asSharedFlow()

    fun showAddSubjectDialog() {
        _state.update { state ->
            state.copy(
                isAddSubjectDialogShown = true,
                newSubjectName = "",
                selectedIcon = SubjectIcon.Trend,
            )
        }
    }

    fun dismissAddSubjectDialog() {
        _state.update { state ->
            state.copy(
                isAddSubjectDialogShown = false,
                newSubjectName = "",
                selectedIcon = SubjectIcon.Trend,
            )
        }
    }

    fun updateNewSubjectName(value: String) {
        _state.update { state -> state.copy(newSubjectName = value) }
    }

    fun selectIcon(icon: SubjectIcon) {
        _state.update { state -> state.copy(selectedIcon = icon) }
    }

    fun addSubject() {
        val title = _state.value.newSubjectName.trim()
        if (title.isBlank()) return

        _state.update { state ->
            val nextSubject = PomodoroSubjectUiModel(
                title = title,
                icon = state.selectedIcon,
                color = subjectColorFor(state.subjects.size),
            )

            state.copy(
                subjects = state.subjects + nextSubject,
                isAddSubjectDialogShown = false,
                newSubjectName = "",
                selectedIcon = SubjectIcon.Trend,
            )
        }
    }

    fun startStudy() {
        viewModelScope.launch {
            _events.emit(PomodoroTimerEvent.StartStudyRequested)
        }
    }

    private fun subjectColorFor(index: Int): SubjectColor {
        val colors = SubjectColor.entries
        return colors[index % colors.size]
    }
}
