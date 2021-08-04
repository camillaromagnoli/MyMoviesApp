package com.example.mymoviesapp.domain.credits.repository

import com.example.mymoviesapp.domain.credits.entity.CreditList
import com.example.mymoviesapp.domain.exception.BaseException

interface CreditRepository {
    @Throws(BaseException::class)
    fun getCredits(movieId: Int, callback: (CreditList?) -> Unit)
}