package com.example.mymoviesapp.domain.movie.repository

import com.example.mymoviesapp.domain.movie.entity.Movie
import com.example.mymoviesapp.domain.movie.entity.MovieList

interface MovieRepository {
    suspend fun getPopularMovies(): MovieList?

    suspend fun getTopRatedMovies(): MovieList?

    suspend fun getUpcomingMovies(): MovieList?

    suspend fun getLocalMovies(): List<Movie>

    suspend fun saveMovies(list: List<Movie>)
}