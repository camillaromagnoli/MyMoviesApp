package com.example.mymoviesapp.data.core

import android.content.Context
import androidx.room.Room
import com.example.mymoviesapp.domain.exception.ClientException
import com.example.mymoviesapp.domain.exception.NetworkException
import com.example.mymoviesapp.domain.movie.entity.AppDatabase
import com.example.mymoviesapp.domain.movie.entity.Movie
import kotlinx.coroutines.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException
import javax.inject.Inject

class BaseRequestSenderImpl @Inject constructor() : BaseRequestSender {

    var database: AppDatabase? = null

    fun initDatabase(context: Context) {
        if (database == null) {
            database = Room.databaseBuilder(context, AppDatabase::class.java, "AppDatabase").build()
        }
    }

    fun addMovies(context: Context, movie: Movie) {
        initDatabase(context)
        CoroutineScope(GlobalScope.coroutineContext).launch {
            withContext(Dispatchers.IO) {
                database?.movieDao()?.insertMovies(movie)
            }
        }
    }

    fun deleteMovie(context: Context, movie: Movie) {
        initDatabase(context)
        CoroutineScope(GlobalScope.coroutineContext).launch {
            withContext(Dispatchers.IO) {
                database?.movieDao()?.deleteMovies(movie)
            }
        }
    }

    fun getMovies(context: Context,callback: (List<Movie>) -> Unit) {
        initDatabase(context)
        CoroutineScope(GlobalScope.coroutineContext).launch {
            withContext(Dispatchers.IO) {
                val listFavorites = database?.movieDao()?.getAllMovies()
                withContext(Dispatchers.Main) {
                    callback(listFavorites ?: listOf())
                }
            }
        }
    }

    override fun <T : Any> getResult(call: Call<T>, callback: (T?) -> Unit) {
        CoroutineScope(GlobalScope.coroutineContext).launch(Dispatchers.Main) {
            withContext(Dispatchers.IO) {
                val callApi = call
                callApi.enqueue(object : Callback<T> {
                    override fun onResponse(call: Call<T>, response: Response<T>) {
                        if (response.isSuccessful) {
                            callback.invoke(response.body())
                        } else throw ClientException(response.message(), response.code())
                    }

                    override fun onFailure(call: Call<T>, t: Throwable) {
                        if (t is IOException)
                            throw NetworkException(t.message, t)
                        else throw Exception()
                    }
                })
            }
        }
    }
}