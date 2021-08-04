package com.example.mymoviesapp.features.filter

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mymoviesapp.domain.genre.entity.Genre
import com.example.mymoviesapp.domain.genre.repository.GenreRepository

class GenreViewModel @ViewModelInject constructor(
    private val genreRepository: GenreRepository
) : ViewModel() {
    private val genres = MutableLiveData<List<Genre>>()
    fun getGenres() = genres

    fun getGenresData() {
        genreRepository.getGenres { genres.postValue(it) }
    }
}