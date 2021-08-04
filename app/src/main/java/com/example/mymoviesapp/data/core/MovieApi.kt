package com.example.mymoviesapp.data.core

import com.example.mymoviesapp.domain.genre.entity.GenresList
import com.example.mymoviesapp.domain.movie.entity.MovieList
import retrofit2.Call
import retrofit2.http.GET

interface MovieApi {
    @GET("3/movie/popular?api_key=$API_KEY&language=en-US&page=1")
    fun getPopularMovies(): Call<MovieList>

    @GET("3/movie/top_rated?api_key=$API_KEY")
    fun getTopRatedMovies(): Call<MovieList>

    @GET("3/movie/upcoming?api_key=$API_KEY")
    fun getUpcomingMovies(): Call<MovieList>

    @GET("3/genre/movie/list?api_key=$API_KEY&language=en-US")
    fun getGenres(): Call<GenresList>

    companion object  {
        private const val API_KEY = "a75a3482c806b4dd11b0cf9a7f0919ec"
    }
}