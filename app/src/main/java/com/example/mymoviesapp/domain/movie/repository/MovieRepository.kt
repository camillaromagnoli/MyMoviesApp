package com.example.mymoviesapp.domain.movie.repository

import com.example.mymoviesapp.data.response.movie.local.database.MovieEntity
import com.example.mymoviesapp.domain.movie.entity.MovieList

interface MovieRepository {
    suspend fun getPopularMovies(): MovieList?

    suspend fun getTopRatedMovies(): MovieList?

    suspend fun getUpcomingMovies(): MovieList?

    suspend fun getLocalMovies(): List<MovieEntity>

    suspend fun saveMovies(list: List<MovieEntity>)
}