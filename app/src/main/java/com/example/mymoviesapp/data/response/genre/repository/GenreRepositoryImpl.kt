package com.example.mymoviesapp.data.response.genre.repository

import com.example.mymoviesapp.data.core.MovieApi
import com.example.mymoviesapp.domain.genre.entity.Genre
import com.example.mymoviesapp.domain.genre.entity.GenresList
import com.example.mymoviesapp.domain.genre.repository.GenreRepository
import kotlinx.coroutines.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class GenreRepositoryImpl @Inject constructor(
    private val movieApi: MovieApi
) : GenreRepository {
    override fun getGenres(callback: (List<Genre>) -> Unit) {
        CoroutineScope(GlobalScope.coroutineContext).launch(Dispatchers.Main) {
            withContext(Dispatchers.IO) {
                val callApi = movieApi.getGenres()
                callApi.enqueue(object : Callback<GenresList> {
                    override fun onResponse(
                        call: Call<GenresList>,
                        response: Response<GenresList>
                    ) {
                        callback(response.body()?.genres ?: mutableListOf())
                    }
                    override fun onFailure(call: Call<GenresList>, t: Throwable) {
                    }
                })
            }
        }
    }
}