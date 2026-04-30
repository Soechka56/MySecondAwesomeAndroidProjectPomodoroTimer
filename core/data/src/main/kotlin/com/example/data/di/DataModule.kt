package com.example.data.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.core.DataStoreFactory
import androidx.datastore.dataStoreFile
import com.example.common.qualifier.ApplicationContext
import com.example.data.datastore.UserPreferences
import com.example.data.datastore.UserPreferencesSerializer
import com.example.data.repository.UserRepositoryImpl
import com.example.domain.repository.UserRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

private const val userPreferencesFileName = "user_preferences.json"

@Module(includes = [DataStoreModule::class])
interface DataModule {
    @Binds
    @Singleton
    fun bindUserRepository(impl: UserRepositoryImpl): UserRepository
}

@Module
object DataStoreModule {
    @Provides
    @Singleton
    fun provideUserPreferencesDataStore(
        @ApplicationContext context: Context,
        serializer: UserPreferencesSerializer
    ): DataStore<UserPreferences> {
        return DataStoreFactory.create(
            serializer = serializer,
            produceFile = {
                context.dataStoreFile(userPreferencesFileName)
            }
        )
    }
}