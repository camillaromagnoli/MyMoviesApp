package com.example.mymoviesapp.data.core

import com.example.mymoviesapp.domain.exception.ClientException
import com.example.mymoviesapp.domain.exception.NetworkException
import kotlinx.coroutines.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException
import javax.inject.Inject

class BaseRequestSenderImpl @Inject constructor() : BaseRequestSender {
    override fun <T : Any> getResult(call: Call<T>, callback: (T?) -> Unit) {
        CoroutineScope(GlobalScope.coroutineContext).launch(Dispatchers.Main) {
            withContext(Dispatchers.IO) {
                val callApi = call
                callApi.enqueue(object : Callback<T> {
                    override fun onResponse(call: Call<T>, response: Response<T>) {
                        if (response.isSuccessful) {
                            callback.invoke(response.body())
                        } else throw ClientException(response.message(), response.code())
                    }

                    override fun onFailure(call: Call<T>, t: Throwable) {
                        if (t is IOException)
                            throw NetworkException(t.message, t)
                        else throw Exception()
                    }
                })
            }
        }
    }
}