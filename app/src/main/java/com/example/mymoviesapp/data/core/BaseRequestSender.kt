package com.example.mymoviesapp.data.core

interface BaseRequestSender {
    suspend fun <T> getResult(call: suspend () -> T): T
}