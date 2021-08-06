package com.example.mymoviesapp.data.response.movie.repository

import com.example.mymoviesapp.data.core.BaseRequestSender
import com.example.mymoviesapp.data.core.MovieApi
import com.example.mymoviesapp.data.response.movie.local.MovieLocalDataSource
import com.example.mymoviesapp.domain.movie.entity.Movie
import com.example.mymoviesapp.domain.movie.repository.MovieRepository
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val movieApi: MovieApi,
    private val baseRequestSender: BaseRequestSender,
    private val movieLocalDataSource: MovieLocalDataSource
) : MovieRepository {
    override suspend fun getPopularMovies() =
        baseRequestSender.getResult { movieApi.getPopularMovies() }


    override suspend fun getTopRatedMovies() =
        baseRequestSender.getResult { movieApi.getTopRatedMovies() }


    override suspend fun getUpcomingMovies() =
        baseRequestSender.getResult { movieApi.getUpcomingMovies() }


    override suspend fun getLocalMovies() =
        movieLocalDataSource.retrieveMovies()

    override suspend fun saveMovies(list: List<Movie>) {
        movieLocalDataSource.insertMovies(list)
    }
}