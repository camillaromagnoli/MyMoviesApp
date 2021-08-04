package com.example.mymoviesapp.data.response.movie.repository

import com.example.mymoviesapp.domain.movie.entity.Movie
import com.example.mymoviesapp.domain.movie.entity.MovieList
import com.example.mymoviesapp.data.core.MovieApi
import com.example.mymoviesapp.domain.movie.repository.MovieRepository
import kotlinx.coroutines.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class MovieRepositoryImpl
@Inject constructor(private val movieApi: MovieApi): MovieRepository {
   override fun getPopularMovies(callback: (List<Movie>) -> Unit) {
        CoroutineScope(GlobalScope.coroutineContext).launch(Dispatchers.Main) {
            withContext(Dispatchers.IO) {
                val callApi = movieApi.getPopularMovies()
                callApi.enqueue(object : Callback<MovieList> {
                    override fun onResponse(call: Call<MovieList>, response: Response<MovieList>) {
                        callback(response.body()?.results ?: mutableListOf())
                    }
                    override fun onFailure(call: Call<MovieList>, t: Throwable) {
                    }
                })
            }
        }
    }

    override fun getTopRatedMovies(callback: (List<Movie>) -> Unit) {
        CoroutineScope(GlobalScope.coroutineContext).launch(Dispatchers.Main) {
            withContext(Dispatchers.IO) {
                val callApi = movieApi.getTopRatedMovies()
                callApi.enqueue(object : Callback<MovieList> {
                    override fun onResponse(call: Call<MovieList>, response: Response<MovieList>) {
                        callback(response.body()?.results ?: mutableListOf())
                    }
                    override fun onFailure(call: Call<MovieList>, t: Throwable) {
                    }
                })
            }
        }
    }

    override fun getUpcomingMovies(callback: (List<Movie>) -> Unit) {
        CoroutineScope(GlobalScope.coroutineContext).launch(Dispatchers.Main) {
            withContext(Dispatchers.IO) {
                val callApi = movieApi.getUpcomingMovies()
                callApi.enqueue(object : Callback<MovieList> {
                    override fun onResponse(call: Call<MovieList>, response: Response<MovieList>) {
                        callback(response.body()?.results ?: mutableListOf())
                    }
                    override fun onFailure(call: Call<MovieList>, t: Throwable) {
                    }
                })
            }
        }
    }
}