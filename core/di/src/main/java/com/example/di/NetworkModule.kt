package com.example.di

import com.example.network.PomodoroApi
import com.example.network.mapper.NetworkErrorMapper
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
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
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

    @Provides
    @Singleton
    fun provideNetworkErrorMapper(
        gson: Gson,
    ): NetworkErrorMapper {
        return NetworkErrorMapper(gson)
    }

    private companion object {
        const val BASE_URL = "http://10.0.2.2:8000/"
    }
}
