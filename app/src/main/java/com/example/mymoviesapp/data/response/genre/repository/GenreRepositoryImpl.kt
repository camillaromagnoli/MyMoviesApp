package com.example.mymoviesapp.data.response.genre.repository

import com.example.mymoviesapp.data.core.BaseRequestSender
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
    private val movieApi: MovieApi,
    private val baseRequestSender: BaseRequestSender
) : GenreRepository {
    override fun getGenres(callback: (GenresList?) -> Unit) {
        baseRequestSender.getResult(movieApi.getGenres(), callback)
    }
}