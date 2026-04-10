package com.example.impl.di

import com.example.api.AuthNavKey
import com.example.impl.AuthScreen
import com.example.impl.AuthViewModel
import com.example.navigation.EntryProviderInstaller
import com.example.navigation.Navigator
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoSet

@Module
object AuthModule {
    @Provides
    @IntoSet
    fun provideAuthEntry(
        navigator: Navigator,
        factory: AuthViewModel.Factory,
    ): EntryProviderInstaller = {
        entry<AuthNavKey> {
            AuthScreen(
                factory = factory,
                onResult = { _ ->
                    navigator.goBack()
                },
            )
        }
    }
}
