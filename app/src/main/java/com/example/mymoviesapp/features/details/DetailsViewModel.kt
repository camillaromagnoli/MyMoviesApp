package com.example.mymoviesapp.features.details

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mymoviesapp.domain.credits.entity.Cast
import com.example.mymoviesapp.domain.credits.repository.CreditRepository

class DetailsViewModel @ViewModelInject constructor(
    private val creditRepository: CreditRepository
) : ViewModel() {
    private val castMembers = MutableLiveData<List<Cast>>()
    fun getCast(): LiveData<List<Cast>> = castMembers

    fun getCredits(movieId: Int?) {
        movieId?.let {
            creditRepository.getCredits(movieId) {
                castMembers.postValue(it)
            }
        }
    }
}