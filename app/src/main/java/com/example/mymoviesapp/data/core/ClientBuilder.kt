package com.example.mymoviesapp.data.core

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ClientBuilder {
    companion object {
        fun <S> createService(
            serviceClass: Class<S>
        ): S = createRetrofitClient().create(serviceClass)

        private const val BASE_URL = "https://api.themoviedb.org/"
        const val BASE_POSTER_URL = "https://image.tmdb.org/t/p/w500"

        private fun createRetrofitClient(): Retrofit {
            return Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .build()
        }
    }
}