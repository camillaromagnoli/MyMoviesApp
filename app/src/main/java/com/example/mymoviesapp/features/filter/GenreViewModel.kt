package com.example.mymoviesapp.features.filter

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mymoviesapp.domain.genre.entity.Genre
import com.example.mymoviesapp.domain.genre.repository.GenreRepository
import com.example.mymoviesapp.domain.state.State
import com.example.mymoviesapp.extensions.postError
import com.example.mymoviesapp.extensions.postLoading
import com.example.mymoviesapp.extensions.postSuccess

class GenreViewModel @ViewModelInject constructor(
    private val genreRepository: GenreRepository
) : ViewModel() {
    private val state = MutableLiveData<State<List<Genre>>>()
    fun getGenresState(): LiveData<State<List<Genre>>> = state

    fun getGenresData() {
        try {
            state.postLoading()
            genreRepository.getGenres {
                state.postSuccess(it?.genres)
            }
        } catch (e: Exception) {
            state.postError(e)
        }
    }
}