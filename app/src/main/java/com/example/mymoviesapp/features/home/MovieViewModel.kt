package com.example.mymoviesapp.features.home

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mymoviesapp.domain.movie.entity.Movie
import com.example.mymoviesapp.domain.movie.repository.MovieRepository

class MovieViewModel @ViewModelInject constructor(private val movieRepository: MovieRepository) :
    ViewModel() {
    private val popularMovies = MutableLiveData<List<Movie>>()
    fun getPopularMovies() = popularMovies

    private val upcomingMovies = MutableLiveData<List<Movie>>()
    fun getUpcomingMovies() = upcomingMovies

    private val topRatedMovies = MutableLiveData<List<Movie>>()
    fun getTopRatedMovies() = topRatedMovies

    fun getMovieData() {
        movieRepository.getPopularMovies { popularMovies.postValue(it) }
        movieRepository.getTopRatedMovies { topRatedMovies.postValue(it) }
        movieRepository.getUpcomingMovies { upcomingMovies.postValue(it) }
    }

    fun filterByGenre(id: Int, movies: List<Movie>?): List<Movie> {
        return movies?.filter { it.genreIds.contains(id) }.orEmpty()
    }
}