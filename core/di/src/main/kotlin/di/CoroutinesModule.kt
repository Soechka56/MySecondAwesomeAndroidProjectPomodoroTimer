package com.example.di

import com.example.common.qualifier.DispatcherIO
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

@Module
class CoroutinesModule {
    @Provides
    @DispatcherIO fun provideIODispatcher(): CoroutineDispatcher = Dispatchers.IO
}