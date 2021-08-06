package com.example.mymoviesapp.domain.genre.repository

import com.example.mymoviesapp.domain.genre.entity.GenresList

interface GenreRepository {
    suspend fun getGenres(): GenresList?
}