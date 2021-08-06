package com.example.mymoviesapp.domain.movie.entity

import android.os.Parcelable
import com.example.mymoviesapp.data.response.movie.local.database.MovieEntity.Companion.FIELD_BACKDROP_PATH
import com.example.mymoviesapp.data.response.movie.local.database.MovieEntity.Companion.FIELD_GENRE_IDS
import com.example.mymoviesapp.data.response.movie.local.database.MovieEntity.Companion.FIELD_ORIGINAL_TITLE
import com.example.mymoviesapp.data.response.movie.local.database.MovieEntity.Companion.FIELD_POSTER_PATH
import com.example.mymoviesapp.data.response.movie.local.database.MovieEntity.Companion.FIELD_RELEASE_DATE
import com.example.mymoviesapp.data.response.movie.local.database.MovieEntity.Companion.FIELD_VOTE_AVERAGE
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Movie(
    val id: Int?,
    val overview: String?,
    val title: String?,
    @SerializedName(FIELD_GENRE_IDS) val genreIds: List<Int>?,
    @SerializedName(FIELD_POSTER_PATH) val posterPath: String?,
    @SerializedName(FIELD_ORIGINAL_TITLE) val originalTitle: String?,
    @SerializedName(FIELD_RELEASE_DATE) val releaseDate: String?,
    @SerializedName(FIELD_VOTE_AVERAGE) val voteAverage: Float?,
    @SerializedName(FIELD_BACKDROP_PATH) val backdropPath: String?,
) : Parcelable
