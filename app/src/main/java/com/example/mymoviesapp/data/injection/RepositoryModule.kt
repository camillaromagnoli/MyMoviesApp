package com.example.mymoviesapp.data.injection

import com.example.mymoviesapp.domain.movie.repository.MovieRepository
import com.example.mymoviesapp.data.response.movie.repository.MovieRepositoryImpl
import com.example.mymoviesapp.data.core.MovieApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun provideMovieRepository(movieApi: MovieApi): MovieRepository {
        return MovieRepositoryImpl(movieApi)
    }
}