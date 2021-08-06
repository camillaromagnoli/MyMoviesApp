package com.example.mymoviesapp.domain.movie.entity

import androidx.room.*

@Dao
interface MovieDAO {

    @Insert
    fun insertMovies(movie: Movie)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllMovies(movie: List<Movie>)

    @Delete
    fun deleteMovies(movie: Movie)

    @Query("SELECT * FROM Movie")
    fun getAllMovies(): List<Movie>
}