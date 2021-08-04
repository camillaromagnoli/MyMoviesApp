package com.example.mymoviesapp.domain.state


class State<D>(val status: Status, val data: D? = null, val throwable: Throwable? = null) {
    companion object {
        fun <D> success(data: D?): State<D> =
            State(status = Status.SUCCESS, data = data, throwable = null)

        fun <D> error(throwable: Throwable?): State<D> =
            State(status = Status.ERROR, data = null, throwable = throwable)

        fun <D> loading(): State<D> =
            State(status = Status.LOADING, data = null, throwable = null)
    }
}