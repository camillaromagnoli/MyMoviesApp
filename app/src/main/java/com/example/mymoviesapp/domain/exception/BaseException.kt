package com.example.mymoviesapp.domain.exception

abstract class BaseException : Exception {
    override val message: String?
        get() = super.message.orEmpty()
    private var code: Int? = null

    constructor(message: String?, code: Int) : super(message) {
        this.code = code
    }

    constructor(message: String?, cause: Throwable) : super(message, cause)
}