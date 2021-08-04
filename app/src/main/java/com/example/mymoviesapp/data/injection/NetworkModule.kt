package com.example.mymoviesapp.data.injection

import com.example.mymoviesapp.data.core.BaseRequestSender
import com.example.mymoviesapp.data.core.BaseRequestSenderImpl
import com.example.mymoviesapp.data.core.ClientBuilder
import com.example.mymoviesapp.data.core.MovieApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun provideApi(): MovieApi {
        return ClientBuilder.createService(MovieApi::class.java)
    }

    @Provides
    fun providesRequestSender(): BaseRequestSender {
        return BaseRequestSenderImpl()
    }
}