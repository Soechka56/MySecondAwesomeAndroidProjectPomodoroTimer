package com.example.pomodorotimer.impl.di

import androidx.compose.runtime.remember
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.navigation.EntryProviderInstaller
import com.example.pomodorotimer.api.PomodoroTimerDependencies
import com.example.pomodorotimer.api.PomodoroTimerNavKey
import com.example.pomodorotimer.impl.PomodoroTimerViewModel
import com.example.viewmodel.ViewModelFactoryModule
import com.example.viewmodel.ViewModelKey
import dagger.Binds
import dagger.Component
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap
import dagger.multibindings.IntoSet

@PomodoroTimerScope
@Component(
    dependencies = [PomodoroTimerDependencies::class],
    modules = [PomodoroTimerViewModelModule::class, ViewModelFactoryModule::class]
)
interface PomodoroTimerComponent {
    fun viewModelFactory(): ViewModelProvider.Factory

    @Component.Factory
    interface Factory {
        fun create(
            dependencies: PomodoroTimerDependencies
        ): PomodoroTimerComponent
    }
}

@Module
interface PomodoroTimerViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(PomodoroTimerViewModel::class)
    fun bindPomodoroTimerViewModel(
        viewModel: PomodoroTimerViewModel
    ): ViewModel
}

@Module
object PomodoroTimerEntryModule {
    @Provides
    @IntoSet
    fun providePomodoroTimerEntry(
        dependencies: PomodoroTimerDependencies
    ): EntryProviderInstaller {
        return {
            entry<PomodoroTimerNavKey> {
                val pomodoroTimerComponent = remember {
                    DaggerPomodoroTimerComponent.factory()
                        .create(dependencies = dependencies)
                }

                val viewModel: PomodoroTimerViewModel = viewModel(
                    factory = pomodoroTimerComponent.viewModelFactory()
                )
            }
        }
    }
}