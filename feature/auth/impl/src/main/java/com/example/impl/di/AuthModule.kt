package com.example.impl.di

import androidx.lifecycle.ViewModel
import com.example.api.AuthNavKey
import com.example.api.ViewModelKey
import com.example.impl.AuthScreen
import com.example.impl.AuthViewModel
import com.example.navigation.EntryProviderInstaller
import com.example.navigation.Navigator
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap
import dagger.multibindings.IntoSet

@Module(
    includes = [ViewModelModule::class]
)
object AuthModule {
    @Provides
    @IntoSet
    fun provideAuthEntry(
        navigator: Navigator,
        viewmodel: AuthViewModel,
    ): EntryProviderInstaller = {
        entry<AuthNavKey> {
            AuthScreen(
                viewModel = viewmodel,
                onResult = { _ ->
                    navigator.goBack()
                },
            )
        }
    }
}

@Module
interface ViewModelModule{
    @Binds
    @IntoMap
    @ViewModelKey(AuthViewModel::class)
    fun bindAuthViewModel(
        viewModel: AuthViewModel
    ): ViewModel
}