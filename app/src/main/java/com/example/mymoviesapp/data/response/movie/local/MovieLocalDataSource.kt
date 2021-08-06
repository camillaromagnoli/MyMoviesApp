package com.example.mymoviesapp.data.response.movie.local

import com.example.mymoviesapp.data.response.movie.local.database.MovieEntity

interface MovieLocalDataSource {

    suspend fun insertMovies(movies: List<MovieEntity>)

    suspend fun retrieveMovies(): List<MovieEntity>

}