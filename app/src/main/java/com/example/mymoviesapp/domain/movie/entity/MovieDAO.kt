package com.example.mymoviesapp.domain.movie.entity

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface MovieDAO {

    @Insert
    fun insertMovies(movie: Movie)

    @Delete
    fun deleteMovies(movie: Movie)

    @Query("SELECT * FROM Movie")
    fun getAllMovies() : List<Movie>
}