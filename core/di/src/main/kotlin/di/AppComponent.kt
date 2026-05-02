package com.example.di

import android.content.Context
import com.example.common.qualifier.ApplicationContext
import com.example.data.di.DataModule
import com.example.di.feature.FeatureDependenciesBindingModule
import com.example.di.feature.FeatureEntryBindingModule
import com.example.navigation.EntryProviderInstaller
import com.example.navigation.Navigator
import com.example.network.di.NetworkModule
import dagger.BindsInstance
import dagger.Component

import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        NetworkModule::class,
        DataModule::class,
        CoroutinesModule::class,

        FeatureEntryBindingModule::class,
        FeatureDependenciesBindingModule::class,
    ]
)
interface AppComponent {
    fun entryProviderInstallers(): Set<EntryProviderInstaller>
    fun navigator(): Navigator

    @Component.Factory
    interface Factory {
        fun create(
            @ApplicationContext @BindsInstance context: Context,
            @BindsInstance startDestination: Any,
        ): AppComponent
    }
}
