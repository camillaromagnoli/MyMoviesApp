package com.example.mymoviesapp.data.response.movie.local

import com.example.mymoviesapp.domain.movie.entity.Movie

interface MovieLocalDataSource {

    suspend fun insertMovies(movies: List<Movie>)

    suspend fun retrieveMovies(): List<Movie>

}