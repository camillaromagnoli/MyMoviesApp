package com.example.mymoviesapp.data.response.movie.local

import com.example.mymoviesapp.data.response.movie.local.database.AppDatabase
import com.example.mymoviesapp.data.response.movie.local.database.MovieEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class MovieLocalDataSourceImpl @Inject constructor(
    private val appDatabase: AppDatabase
) : MovieLocalDataSource {
    private val movieDao by lazy { appDatabase.movieDao() }

    override suspend fun insertMovies(movies: List<MovieEntity>) = withContext(Dispatchers.IO) {
        movieDao.insertAllMovies(movies)
    }

    override suspend fun retrieveMovies() = withContext(Dispatchers.IO) {
        movieDao.getAllMovies()
    }
}