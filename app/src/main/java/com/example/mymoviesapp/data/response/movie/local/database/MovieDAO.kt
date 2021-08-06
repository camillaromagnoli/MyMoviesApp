package com.example.mymoviesapp.data.response.movie.local.database

import androidx.room.*

@Dao
interface MovieDAO {

    @Insert
    fun insertMovies(movie: MovieEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllMovies(movie: List<MovieEntity>)

    @Delete
    fun deleteMovies(movie: MovieEntity)

    @Query("SELECT * FROM MovieEntity")
    fun getAllMovies(): List<MovieEntity>
}