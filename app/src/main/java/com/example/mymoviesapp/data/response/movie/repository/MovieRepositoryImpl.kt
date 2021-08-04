package com.example.mymoviesapp.data.response.movie.repository

import com.example.mymoviesapp.data.core.BaseRequestSender
import com.example.mymoviesapp.data.core.MovieApi
import com.example.mymoviesapp.domain.movie.entity.MovieList
import com.example.mymoviesapp.domain.movie.repository.MovieRepository
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val movieApi: MovieApi,
    private val baseRequestSender: BaseRequestSender
) : MovieRepository {
    override fun getPopularMovies(callback: (MovieList?) -> Unit) {
        baseRequestSender.getResult(movieApi.getPopularMovies(), callback)
    }

    override fun getTopRatedMovies(callback: (MovieList?) -> Unit) {
        baseRequestSender.getResult(movieApi.getTopRatedMovies(), callback)
    }

    override fun getUpcomingMovies(callback: (MovieList?) -> Unit) {
        baseRequestSender.getResult(movieApi.getUpcomingMovies(), callback)
    }
}