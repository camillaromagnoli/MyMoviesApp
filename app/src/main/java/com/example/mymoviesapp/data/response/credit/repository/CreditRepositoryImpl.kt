package com.example.mymoviesapp.data.response.credit.repository

import com.example.mymoviesapp.data.core.MovieApi
import com.example.mymoviesapp.domain.credits.entity.Cast
import com.example.mymoviesapp.domain.credits.entity.CreditList
import com.example.mymoviesapp.domain.credits.repository.CreditRepository
import kotlinx.coroutines.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class CreditRepositoryImpl @Inject constructor(
    private val movieApi: MovieApi
) : CreditRepository {
    override fun getCredits(movieId: Int, callback: (List<Cast>) -> Unit) {
        CoroutineScope(GlobalScope.coroutineContext).launch(Dispatchers.Main) {
            withContext(Dispatchers.IO) {
                val callApi = movieApi.getCredits(movieId)
                callApi.enqueue(object : Callback<CreditList> {
                    override fun onResponse(
                        call: Call<CreditList>,
                        response: Response<CreditList>
                    ) {
                        callback(response.body()?.cast ?: mutableListOf())
                    }

                    override fun onFailure(call: Call<CreditList>, t: Throwable) {
                    }
                })
            }
        }
    }
}