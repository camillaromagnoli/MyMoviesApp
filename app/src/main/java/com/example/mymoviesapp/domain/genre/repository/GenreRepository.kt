package com.example.mymoviesapp.domain.genre.repository

import com.example.mymoviesapp.domain.genre.entity.Genre

interface GenreRepository {

    fun getGenres(callback: (List<Genre>) -> Unit)
}