package com.example.mymoviesapp.domain.credits.repository

import com.example.mymoviesapp.domain.credits.entity.Cast

interface CreditRepository {
    fun getCredits(movieId: Int, callback: (List<Cast>) -> Unit)
}