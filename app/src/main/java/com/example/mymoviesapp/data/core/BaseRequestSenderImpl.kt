package com.example.mymoviesapp.data.core

import com.example.mymoviesapp.domain.exception.ClientException
import com.example.mymoviesapp.domain.exception.NetworkException
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class BaseRequestSenderImpl @Inject constructor() : BaseRequestSender {
    override suspend fun <T> getResult(call: suspend () -> T): T {
        try {
            return call()
        } catch (e: HttpException) {
            throw ClientException(e.message(), e.code())
        } catch (e: IOException) {
            throw NetworkException(e.message, e)
        } catch (e: Exception) {
            throw e
        }
    }
}