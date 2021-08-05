package com.example.mymoviesapp.domain.movie.entity

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity
data class Movie(
    @ColumnInfo(name = "title")
    val title: String?,

    @PrimaryKey val id: Int?,
    val overview: String?,

    @SerializedName("genre_ids") val genreIds: List<Int>,

    @ColumnInfo(name = "poster_path")
    @SerializedName("poster_path") val posterPath: String?,

    @ColumnInfo(name = "original_title")
    @SerializedName("original_title") val originalTitle: String?,

    @ColumnInfo(name = "release_date")
    @SerializedName("release_date") val releaseDate: String?,

    @ColumnInfo(name = "vote_average")
    @SerializedName("vote_average") val voteAverage: Float?,

    @ColumnInfo(name = "backdrop_path")
    @SerializedName("backdrop_path") val backdropPath: String?
) : Parcelable