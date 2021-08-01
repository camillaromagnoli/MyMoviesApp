package com.example.mymoviesapp.data

import com.example.mymoviesapp.models.MovieApi
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
}