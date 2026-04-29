package com.example.di

import com.example.build_config.BuildConfigProvider
import com.example.network.PomodoroApi
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class NetworkModule {
    @Provides
    @Singleton
    fun provideGson(): Gson {
        return Gson()
    }

    @Provides
    @Singleton
    fun provideRetrofit(
        gson: Gson,
        buildConfig: BuildConfigProvider
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(buildConfig.getBaseUrl())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }

    @Provides
    @Singleton
    fun providePomodoroApi(
        retrofit: Retrofit,
    ): PomodoroApi {
        return retrofit.create(PomodoroApi::class.java)
    }
}
