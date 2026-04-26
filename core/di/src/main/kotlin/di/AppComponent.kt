package com.example.di

import android.content.Context
import com.example.impl.di.AuthEntryModule
import com.example.navigation.EntryProviderInstaller
import com.example.navigation.Navigator
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        NetworkModule::class,
        DataModule::class,
        AuthEntryModule::class,
    ]
)
interface AppComponent {

    fun navigator(): Navigator
    fun entryProviderInstallers(): Set<EntryProviderInstaller>

    @Component.Factory
    interface Factory {
        fun create(
            @BindsInstance context: Context,
            @BindsInstance startDestination: Any,
        ): AppComponent
    }
}
