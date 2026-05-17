package com.example.studysession.impl.di

import androidx.compose.runtime.remember
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.navigation.EntryProviderInstaller
import com.example.studysession.api.StudySessionDependencies
import com.example.studysession.api.StudySessionNavKey
import com.example.studysession.impl.StudySessionScreen
import com.example.studysession.impl.StudySessionViewModel
import com.example.viewmodel.ViewModelFactoryModule
import com.example.viewmodel.ViewModelKey
import dagger.Binds
import dagger.Component
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap
import dagger.multibindings.IntoSet

@StudySessionScope
@Component(
    dependencies = [StudySessionDependencies::class],
    modules = [StudySessionViewModelModule::class, ViewModelFactoryModule::class]
)
interface StudySessionComponent {
    fun viewModelFactory(): ViewModelProvider.Factory

    @Component.Factory
    interface Factory {
        fun create(
            dependencies: StudySessionDependencies
        ): StudySessionComponent
    }
}

@Module
interface StudySessionViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(StudySessionViewModel::class)
    fun bindStudySessionViewModel(
        viewModel: StudySessionViewModel
    ): ViewModel
}

@Module
object StudySessionEntryModule {
    @Provides
    @IntoSet
    fun provideStudySessionEntry(
        dependencies: StudySessionDependencies
    ): EntryProviderInstaller {
        return {
            entry<StudySessionNavKey> {
                val studySessionComponent = remember {
                    DaggerStudySessionComponent.factory()
                        .create(dependencies = dependencies)
                }

                val viewModel: StudySessionViewModel = viewModel(
                    factory = studySessionComponent.viewModelFactory()
                )

                StudySessionScreen(
                    viewModel = viewModel,
                )
            }
        }
    }
}
