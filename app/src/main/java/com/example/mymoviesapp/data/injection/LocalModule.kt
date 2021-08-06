package com.example.mymoviesapp.data.injection

import android.content.Context
import androidx.room.Room
import com.example.mymoviesapp.data.response.movie.local.MovieLocalDataSource
import com.example.mymoviesapp.data.response.movie.local.MovieLocalDataSourceImpl
import com.example.mymoviesapp.data.response.movie.local.database.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext

@Module
@InstallIn(ApplicationComponent::class)
object LocalModule {
    @Provides
    fun provideMovieLocalDataSource(appDatabase: AppDatabase): MovieLocalDataSource {
        return MovieLocalDataSourceImpl(appDatabase)
    }

    @Provides
    fun providesAppDb(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java, "database-movies"
        ).build()
    }
}