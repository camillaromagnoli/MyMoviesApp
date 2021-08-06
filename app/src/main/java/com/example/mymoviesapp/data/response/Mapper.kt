package com.example.mymoviesapp.data.response

interface Mapper<D, E> {
    fun toDomain(): D
    fun toEntity(domain: D): E
}