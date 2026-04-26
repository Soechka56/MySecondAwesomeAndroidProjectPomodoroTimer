package com.example.impl.di

import android.content.Context
import androidx.compose.runtime.remember
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.api.AuthDependencies
import com.example.api.AuthNavKey
import com.example.domain.LogInUseCase
import com.example.domain.SignInUseCase
import com.example.impl.AuthScreen
import com.example.impl.AuthViewModel
import com.example.navigation.EntryProviderInstaller
import com.example.navigation.Navigator
import com.example.viewmodel.ViewModelFactoryModule
import com.example.viewmodel.ViewModelKey
import dagger.Binds
import dagger.Component
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap
import dagger.multibindings.IntoSet

@AuthScope
@Component(
    dependencies = [AuthDependencies::class],
    modules = [AuthViewModelModule::class, ViewModelFactoryModule::class]
)
interface AuthComponent {

    fun viewModelFactory(): ViewModelProvider.Factory

    @Component.Factory
    interface Factory {
        fun create(
            dependencies: AuthDependencies
        ): AuthComponent
    }
}


@Module
interface AuthViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(AuthViewModel::class)
    fun bindAuthViewModel(
        viewModel: AuthViewModel
    ): ViewModel
}

@Module
object AuthEntryModule {
    @Provides
    @IntoSet
    fun provideAuthEntry(
        context: Context,
        navigator: Navigator,
        logInUseCase: LogInUseCase,
        signInUseCase: SignInUseCase,
    ): EntryProviderInstaller {
        val dependencies = object : AuthDependencies {
            override fun logInUseCase(): LogInUseCase = logInUseCase
            override fun signInUseCase(): SignInUseCase = signInUseCase
            override fun navigator(): Navigator = navigator
            override fun context(): Context = context
        }

        return {
            entry<AuthNavKey> {
                val authComponent = remember(dependencies) {
                    DaggerAuthComponent.factory()
                        .create(dependencies = dependencies)
                }

                val viewModelFactory = remember(authComponent) {
                    authComponent.viewModelFactory()
                }

                val viewModel: AuthViewModel = viewModel(
                    factory = viewModelFactory,
                )

                AuthScreen(
                    viewModel = viewModel,
                    onResult = {
                        dependencies.navigator().goBack()
                    },
                )
            }
        }
    }
}
