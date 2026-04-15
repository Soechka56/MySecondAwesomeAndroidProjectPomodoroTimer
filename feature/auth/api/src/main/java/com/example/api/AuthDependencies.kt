package com.example.api

import androidx.lifecycle.ViewModelProvider

interface AuthDependencies {
    fun viewModelFactory(): ViewModelProvider.Factory
}