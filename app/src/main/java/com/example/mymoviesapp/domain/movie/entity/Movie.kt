package com.example.mymoviesapp.domain.movie.entity

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Movie(
    val title: String?,
    val id: Int?,
    @SerializedName("poster_path") val posterPath: String?,
    val overview: String?,
    @SerializedName("original_title") val originalTitle: String?,
    @SerializedName("release_date") val releaseDate: String?,
    @SerializedName("vote_average") val voteAverage: Float?,
    @SerializedName("backdrop_path") val backdropPath: String?
) : Parcelable