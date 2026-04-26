package com.example.navigation

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.navigation3.runtime.EntryProviderScope
import javax.inject.Inject
import javax.inject.Singleton

typealias EntryProviderInstaller = EntryProviderScope<Any>.() -> Unit
@Singleton
class Navigator @Inject constructor(startDestination: Any) {
    val backStack: SnapshotStateList<Any> = mutableStateListOf(startDestination)

    fun goTo(destination: Any) {
        backStack.add(destination)
    }

    fun goBack() {
        backStack.removeLastOrNull()
    }
}
