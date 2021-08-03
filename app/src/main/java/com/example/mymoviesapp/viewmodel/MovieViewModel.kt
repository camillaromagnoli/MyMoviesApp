package com.example.mymoviesapp.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mymoviesapp.domain.movie.repository.MovieRepository
import com.example.mymoviesapp.domain.movie.entity.Movie

class MovieViewModel @ViewModelInject constructor(private val movieRepository: MovieRepository): ViewModel() {
    private val movies = MutableLiveData<List<Movie>>()
    fun getMovies() = movies

    fun getMovieData() {
        movieRepository.getPopularMovies{ movies.postValue(it) }
    }

}