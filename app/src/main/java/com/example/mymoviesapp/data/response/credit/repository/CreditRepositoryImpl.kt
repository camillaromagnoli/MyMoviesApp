package com.example.mymoviesapp.data.response.credit.repository

import com.example.mymoviesapp.data.core.BaseRequestSender
import com.example.mymoviesapp.data.core.MovieApi
import com.example.mymoviesapp.domain.credits.entity.CreditList
import com.example.mymoviesapp.domain.credits.repository.CreditRepository
import javax.inject.Inject

class CreditRepositoryImpl @Inject constructor(
    private val movieApi: MovieApi,
    private val baseRequestSender: BaseRequestSender
) : CreditRepository {
    override fun getCredits(movieId: Int, callback: (CreditList?) -> Unit) {
        baseRequestSender.getResult(movieApi.getCredits(movieId), callback)
    }
}