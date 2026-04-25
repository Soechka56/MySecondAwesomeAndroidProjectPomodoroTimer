package com.example.di.vmfactory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import javax.inject.Provider
import javax.inject.Inject

class MultibindingViewModelFactory @Inject constructor(
    private val viewModels: Map<
            Class<out ViewModel>,
            @JvmSuppressWildcards Provider<ViewModel>>
): ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        val provider = viewModels[modelClass]
            ?: viewModels.entries.firstOrNull{
                entry ->
                modelClass.isAssignableFrom(entry.key)
            }?.value ?: error("undefined type of vm")
        return provider.get() as T
    }
}