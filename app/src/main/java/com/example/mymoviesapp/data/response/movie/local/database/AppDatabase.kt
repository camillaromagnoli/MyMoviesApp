package com.example.mymoviesapp.data.response.movie.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.mymoviesapp.domain.movie.entity.Movie

@Database(entities = [MovieEntity::class], version = 1)
abstract class AppDatabase: RoomDatabase() {
    abstract fun movieDao() : MovieDAO
}