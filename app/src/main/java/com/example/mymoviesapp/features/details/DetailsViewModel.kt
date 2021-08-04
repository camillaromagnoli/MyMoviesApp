package com.example.mymoviesapp.features.details

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mymoviesapp.domain.credits.entity.Cast
import com.example.mymoviesapp.domain.credits.repository.CreditRepository
import com.example.mymoviesapp.domain.movie.entity.Movie
import com.example.mymoviesapp.domain.state.State
import com.example.mymoviesapp.extensions.postError
import com.example.mymoviesapp.extensions.postLoading
import com.example.mymoviesapp.extensions.postSuccess

class DetailsViewModel @ViewModelInject constructor(
    private val creditRepository: CreditRepository
) : ViewModel() {
    private val state = MutableLiveData<State<List<Cast>>>()
    fun getCastMembersState(): LiveData<State<List<Cast>>> = state

    var movie: Movie? = null

    fun getCredits(movieId: Int?) {
        movieId?.let {
            try {
                state.postLoading()
                creditRepository.getCredits(movieId) {
                    state.postSuccess(it?.cast)
                }
            } catch (e: Exception) {
                state.postError(e)
            }
        }
    }
}