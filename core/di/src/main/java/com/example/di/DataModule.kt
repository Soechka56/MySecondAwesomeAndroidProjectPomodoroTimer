package com.example.di

import com.example.data.mapper.UserDataMapper
import com.example.data.repository.UserRepositoryImpl
import com.example.domain.repository.UserRepository
import com.example.network.PomodoroApi
import com.example.network.mapper.NetworkErrorMapper
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DataModule {
    @Provides
    @Singleton
    fun provideUserDataMapper(): UserDataMapper {
        return UserDataMapper()
    }

    @Provides
    @Singleton
    fun provideUserRepository(
        pomodoroApi: PomodoroApi,
        userDataMapper: UserDataMapper,
        networkErrorMapper: NetworkErrorMapper,
    ): UserRepository {
        return UserRepositoryImpl(
            pomodoroApi = pomodoroApi,
            userDataMapper = userDataMapper,
            networkErrorMapper = networkErrorMapper,
        )
    }
}
