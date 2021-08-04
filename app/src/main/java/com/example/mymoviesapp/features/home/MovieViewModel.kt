package com.example.mymoviesapp.features.home

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mymoviesapp.domain.movie.entity.Movie
import com.example.mymoviesapp.domain.movie.repository.MovieRepository
import com.example.mymoviesapp.domain.state.State
import com.example.mymoviesapp.extensions.postError
import com.example.mymoviesapp.extensions.postLoading
import com.example.mymoviesapp.extensions.postSuccess
import java.lang.Exception

class MovieViewModel @ViewModelInject constructor(
    private val movieRepository: MovieRepository
) : ViewModel() {
    private val state = MutableLiveData<State<Unit>>()
    fun getViewState(): LiveData<State<Unit>> = state

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
        try {
            state.postLoading()
            movieRepository.getPopularMovies {
                state.postSuccess()
                allPopularMovies.addAll(it?.results.orEmpty())
                popularMovies.postValue(allPopularMovies)
            }
            movieRepository.getTopRatedMovies {
                state.postSuccess()
                allTopRatedMovies.addAll(it?.results.orEmpty())
                topRatedMovies.postValue(allTopRatedMovies)
            }
            movieRepository.getUpcomingMovies {
                state.postSuccess()
                allUpcomingMovies.addAll(it?.results.orEmpty())
                upcomingMovies.postValue(allUpcomingMovies)
            }
        } catch (e: Exception) {
            state.postError(e)
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