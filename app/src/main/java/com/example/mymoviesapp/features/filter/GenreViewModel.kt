package com.example.mymoviesapp.features.filter

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mymoviesapp.domain.genre.entity.Genre
import com.example.mymoviesapp.domain.genre.repository.GenreRepository
import com.example.mymoviesapp.domain.state.State
import com.example.mymoviesapp.extensions.postError
import com.example.mymoviesapp.extensions.postLoading
import com.example.mymoviesapp.extensions.postSuccess
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

class GenreViewModel @ViewModelInject constructor(
    private val genreRepository: GenreRepository
) : ViewModel() {
    private val state = MutableLiveData<State<List<Genre>>>()
    fun getGenresState(): LiveData<State<List<Genre>>> = state

    init {
        getGenresData()
    }

    fun getGenresData() {
        viewModelScope.launch {
            state.postLoading()
            try {
                coroutineScope {
                    val genresDeferred = async { genreRepository.getGenres() }
                    val apiGenresList = genresDeferred.await()
                    state.postSuccess(apiGenresList?.genres)
                }
            } catch (e: Exception) {
                state.postError(e)
            }
        }
    }
}