package com.example.mymoviesapp.domain.genre.repository

import com.example.mymoviesapp.domain.exception.BaseException
import com.example.mymoviesapp.domain.genre.entity.GenresList

interface GenreRepository {
    @Throws(BaseException::class)
    fun getGenres(callback: (GenresList?) -> Unit)
}