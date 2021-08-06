package com.example.mymoviesapp.data.injection

import com.example.mymoviesapp.data.core.BaseRequestSender
import com.example.mymoviesapp.data.core.MovieApi
import com.example.mymoviesapp.data.response.credit.repository.CreditRepositoryImpl
import com.example.mymoviesapp.data.response.genre.repository.GenreRepositoryImpl
import com.example.mymoviesapp.data.response.movie.local.MovieLocalDataSource
import com.example.mymoviesapp.data.response.movie.repository.MovieRepositoryImpl
import com.example.mymoviesapp.domain.credits.repository.CreditRepository
import com.example.mymoviesapp.domain.genre.repository.GenreRepository
import com.example.mymoviesapp.domain.movie.repository.MovieRepository
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
    fun provideMovieRepository(
        movieApi: MovieApi,
        baseRequestSender: BaseRequestSender,
        movieLocalDataSource: MovieLocalDataSource
    ): MovieRepository {
        return MovieRepositoryImpl(movieApi, baseRequestSender, movieLocalDataSource)
    }

    @Singleton
    @Provides
    fun provideGenreRepository(
        movieApi: MovieApi,
        baseRequestSender: BaseRequestSender
    ): GenreRepository {
        return GenreRepositoryImpl(movieApi, baseRequestSender)
    }

    @Singleton
    @Provides
    fun provideCreditRepository(
        movieApi: MovieApi,
        baseRequestSender: BaseRequestSender
    ): CreditRepository {
        return CreditRepositoryImpl(movieApi, baseRequestSender)
    }
}