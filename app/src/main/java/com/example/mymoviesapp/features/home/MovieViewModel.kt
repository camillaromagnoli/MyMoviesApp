package com.example.mymoviesapp.features.home

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mymoviesapp.domain.movie.entity.Movie
import com.example.mymoviesapp.domain.movie.repository.MovieRepository

class MovieViewModel @ViewModelInject constructor(
    private val movieRepository: MovieRepository
) : ViewModel() {
    private val popularMovies = MutableLiveData<List<Movie>>()
    fun getPopularMovies(): LiveData<List<Movie>> = popularMovies

    private val upcomingMovies = MutableLiveData<List<Movie>>()
    fun getUpcomingMovies(): LiveData<List<Movie>> = upcomingMovies

    private val topRatedMovies = MutableLiveData<List<Movie>>()
    fun getTopRatedMovies(): LiveData<List<Movie>> = topRatedMovies

    private val allTopRatedMovies = mutableListOf<Movie>()
    private val allUpcomingMovies = mutableListOf<Movie>()
    private val allPopularMovies = mutableListOf<Movie>()

    fun getMovieData() {
        movieRepository.getPopularMovies {
            allPopularMovies.addAll(it)
            popularMovies.postValue(allPopularMovies)
        }
        movieRepository.getTopRatedMovies {
            allTopRatedMovies.addAll(it)
            topRatedMovies.postValue(allTopRatedMovies)
        }
        movieRepository.getUpcomingMovies {
            allUpcomingMovies.addAll(it)
            upcomingMovies.postValue(allUpcomingMovies)
        }
    }

    fun filterByGenre(id: Int) {
        upcomingMovies.postValue(allUpcomingMovies.filter { it.genreIds.contains(id) })
        topRatedMovies.postValue(allTopRatedMovies.filter { it.genreIds.contains(id) })
        popularMovies.postValue(allPopularMovies.filter { it.genreIds.contains(id) })
    }

    fun resetFilters() {
        upcomingMovies.postValue(allUpcomingMovies)
        topRatedMovies.postValue(allTopRatedMovies)
        popularMovies.postValue(allPopularMovies)
    }
}