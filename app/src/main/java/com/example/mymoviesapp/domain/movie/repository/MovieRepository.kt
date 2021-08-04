package com.example.mymoviesapp.domain.movie.repository

import com.example.mymoviesapp.domain.movie.entity.Movie

interface MovieRepository {

    fun getPopularMovies(callback: (List<Movie>) -> Unit)
    fun getTopRatedMovies(callback: (List<Movie>) -> Unit)
    fun getUpcomingMovies(callback: (List<Movie>) -> Unit)
}