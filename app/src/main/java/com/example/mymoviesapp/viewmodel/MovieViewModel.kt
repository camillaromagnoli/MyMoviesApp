package com.example.mymoviesapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mymoviesapp.models.Movie
import com.example.mymoviesapp.models.MovieRepository

class MovieViewModel: ViewModel() {
    private val movies = MutableLiveData<List<Movie>>()
    fun getMovies() = movies

    fun getMovieData() {
        MovieRepository.getPopular { movies.postValue(it) }
    }

}