package com.example.mymoviesapp.data.core

import com.example.mymoviesapp.domain.exception.BaseException
import retrofit2.Call

interface BaseRequestSender {
    @Throws(BaseException::class)
    fun <T : Any> getResult(call: Call<T>, callback: (T?) -> Unit)
}