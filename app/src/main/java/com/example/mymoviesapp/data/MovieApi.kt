package com.example.mymoviesapp.models

import com.example.mymoviesapp.domain.MovieList
import retrofit2.Call
import retrofit2.http.GET

interface MovieApi {
    @GET("3/movie/popular?api_key=$API_KEY&language=en-US&page=1")
    fun getPopularMovies(): Call<MovieList>

    companion object  {
        const val API_KEY = "a75a3482c806b4dd11b0cf9a7f0919ec"
    }
}

