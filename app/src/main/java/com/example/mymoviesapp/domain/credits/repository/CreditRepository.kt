package com.example.mymoviesapp.domain.credits.repository

import com.example.mymoviesapp.domain.credits.entity.CreditList

interface CreditRepository {
    suspend fun getCredits(movieId: Int): CreditList?
}