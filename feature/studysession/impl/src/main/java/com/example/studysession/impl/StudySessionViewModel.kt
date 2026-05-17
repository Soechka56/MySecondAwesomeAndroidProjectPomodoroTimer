package com.example.studysession.impl

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

class StudySessionViewModel @Inject constructor() : ViewModel() {

    private val _state = MutableStateFlow(StudySessionState())
    val state = _state.asStateFlow()

    fun onActionClick() {
        // play/pause, heart, settings clicks etc.
    }
}
