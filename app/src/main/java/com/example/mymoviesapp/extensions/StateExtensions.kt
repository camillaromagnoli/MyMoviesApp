package com.example.mymoviesapp.extensions

import androidx.lifecycle.MutableLiveData
import com.example.mymoviesapp.domain.state.State
import com.example.mymoviesapp.domain.state.Status

fun <D> State<D>.handleState(
    success: (D?) -> Unit = {},
    error: (Throwable?) -> Unit = {},
    loading: () -> Unit = {},
    stopLoading: () -> Unit = {}
): State<D> {
    when (this.status) {
        Status.LOADING -> { loading() }
        Status.SUCCESS -> {
            success(data)
            stopLoading()
        }
        Status.ERROR -> {
            error(throwable)
            stopLoading()
        }
    }
    return this
}

fun <D> MutableLiveData<State<D>>.postSuccess(data: D? = null) {
    this.postValue(State.success(data))
}

fun <D> MutableLiveData<State<D>>.postError(throwable: Throwable) {
    this.postValue(State.error(throwable))
}

fun <D> MutableLiveData<State<D>>.postLoading() {
    this.postValue(State.loading())
}