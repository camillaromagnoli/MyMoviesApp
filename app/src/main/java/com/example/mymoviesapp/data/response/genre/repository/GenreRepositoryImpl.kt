package com.example.mymoviesapp.data.response.genre.repository

import com.example.mymoviesapp.data.core.BaseRequestSender
import com.example.mymoviesapp.data.core.MovieApi
import com.example.mymoviesapp.domain.genre.repository.GenreRepository
import javax.inject.Inject

class GenreRepositoryImpl @Inject constructor(
    private val movieApi: MovieApi,
    private val baseRequestSender: BaseRequestSender
) : GenreRepository {
    override suspend fun getGenres() =
        baseRequestSender.getResult { movieApi.getGenres() }
}