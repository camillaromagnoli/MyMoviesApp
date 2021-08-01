package com.example.mymoviesapp.data

import com.example.mymoviesapp.domain.Movie

interface MovieRepository {

    fun getPopularMovies(callback: (List<Movie>) -> Unit)
}