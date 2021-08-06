package com.example.mymoviesapp.data.response.credit.repository

import com.example.mymoviesapp.data.core.BaseRequestSender
import com.example.mymoviesapp.data.core.MovieApi
import com.example.mymoviesapp.domain.credits.repository.CreditRepository
import javax.inject.Inject

class CreditRepositoryImpl @Inject constructor(
    private val movieApi: MovieApi,
    private val baseRequestSender: BaseRequestSender
) : CreditRepository {
    override suspend fun getCredits(movieId: Int) =
        baseRequestSender.getResult { movieApi.getCredits(movieId) }

}