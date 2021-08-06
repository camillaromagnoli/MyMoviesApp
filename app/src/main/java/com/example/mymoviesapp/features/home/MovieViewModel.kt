package com.example.mymoviesapp.features.home

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mymoviesapp.data.response.movie.local.database.MovieEntity
import com.example.mymoviesapp.data.response.movie.local.database.MovieEntity.Companion.POPULAR
import com.example.mymoviesapp.data.response.movie.local.database.MovieEntity.Companion.TOP_RATED
import com.example.mymoviesapp.data.response.movie.local.database.MovieEntity.Companion.UPCOMING
import com.example.mymoviesapp.domain.movie.entity.Movie
import com.example.mymoviesapp.domain.movie.repository.MovieRepository
import com.example.mymoviesapp.domain.state.State
import com.example.mymoviesapp.extensions.postError
import com.example.mymoviesapp.extensions.postLoading
import com.example.mymoviesapp.extensions.postSuccess
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

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

    init {
        getMovieData()
    }

    fun getMovieData() {
        viewModelScope.launch {
            state.postLoading()
            try {
                coroutineScope {
                    val popularMoviesDeferred = async { movieRepository.getPopularMovies() }
                    val upcomingMoviesDeferred = async { movieRepository.getUpcomingMovies() }
                    val topRatedMoviesDeferred = async { movieRepository.getTopRatedMovies() }

                    val apiPopularMovies = popularMoviesDeferred.await()
                    val apiUpcomingMovies = upcomingMoviesDeferred.await()
                    val apiTopRatedMovies = topRatedMoviesDeferred.await()

                    allPopularMovies.addAll(apiPopularMovies?.results.orEmpty())
                    allUpcomingMovies.addAll(apiUpcomingMovies?.results.orEmpty())
                    allTopRatedMovies.addAll(apiTopRatedMovies?.results.orEmpty())

                    saveLocalMovies()
                    notifyMoviesLiveData()

                    state.postSuccess()
                }
            } catch (e: Exception) {
                val localMovies = movieRepository.getLocalMovies()
                if (localMovies.isNullOrEmpty()) {
                    state.postError(e)
                } else {
                    state.postSuccess()
                    allPopularMovies.addAll(localMovies.filter { it.category == POPULAR }
                        .map { it.toDomain() })
                    allUpcomingMovies.addAll(localMovies.filter { it.category == UPCOMING }
                        .map { it.toDomain() })
                    allTopRatedMovies.addAll(localMovies.filter { it.category == TOP_RATED }
                        .map { it.toDomain() })
                    notifyMoviesLiveData()
                }
            }
        }
    }

    private fun notifyMoviesLiveData() {
        popularMovies.postValue(allPopularMovies)
        upcomingMovies.postValue(allUpcomingMovies)
        topRatedMovies.postValue(allTopRatedMovies)
    }

    private suspend fun saveLocalMovies() {
        val popularMovies =
            allPopularMovies.map { MovieEntity().toEntity(it).withCategory(POPULAR) }

        val upcomingMovies =
            allUpcomingMovies.map { MovieEntity().toEntity(it).withCategory(UPCOMING) }

        val topRatedMovies =
            allTopRatedMovies.map { MovieEntity().toEntity(it).withCategory(TOP_RATED) }

        val allMovies = mutableListOf<MovieEntity>()
        allMovies.addAll(popularMovies)
        allMovies.addAll(topRatedMovies)
        allMovies.addAll(upcomingMovies)

        movieRepository.saveMovies(allMovies)
    }

    fun filterByGenre(id: Int) {
        upcomingMovies.postValue(allUpcomingMovies.filter { it.genreIds?.contains(id) == true })
        topRatedMovies.postValue(allTopRatedMovies.filter { it.genreIds?.contains(id) == true })
        popularMovies.postValue(allPopularMovies.filter { it.genreIds?.contains(id) == true })
    }

    fun resetFilters() {
        notifyMoviesLiveData()
    }
}