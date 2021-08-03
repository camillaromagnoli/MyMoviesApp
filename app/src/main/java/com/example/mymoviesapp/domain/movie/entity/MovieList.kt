package com.example.mymoviesapp.domain.movie.entity

import com.example.mymoviesapp.domain.movie.entity.Movie

data class MovieList(val page: Int, val results: List<Movie>)
