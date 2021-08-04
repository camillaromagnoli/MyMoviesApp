package com.example.mymoviesapp.domain.movie.repository

import com.example.mymoviesapp.domain.exception.BaseException
import com.example.mymoviesapp.domain.movie.entity.MovieList

interface MovieRepository {
    @Throws(BaseException::class)
    fun getPopularMovies(callback: (MovieList?) -> Unit)

    @Throws(BaseException::class)
    fun getTopRatedMovies(callback: (MovieList?) -> Unit)

    @Throws(BaseException::class)
    fun getUpcomingMovies(callback: (MovieList?) -> Unit)
}