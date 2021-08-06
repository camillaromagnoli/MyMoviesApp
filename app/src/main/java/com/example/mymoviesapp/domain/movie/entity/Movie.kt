package com.example.mymoviesapp.domain.movie.entity

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity
data class Movie(

    @PrimaryKey
    @ColumnInfo(name = FIELD_ID) val id: Int?,

    @ColumnInfo(name = FIELD_OVERVIEW) val overview: String?,

    @ColumnInfo(name = FIELD_TITLE) val title: String?,

    @Ignore
    @SerializedName(FIELD_GENRES_ID) val genreIds: List<Int>?,

    @ColumnInfo(name = FIELD_POSTER_PATH)
    @SerializedName(FIELD_POSTER_PATH) val posterPath: String?,

    @ColumnInfo(name = FIELD_ORIGINAL_TITLE)
    @SerializedName(FIELD_ORIGINAL_TITLE) val originalTitle: String?,

    @ColumnInfo(name = FIELD_RELEASE_DATE)
    @SerializedName(FIELD_RELEASE_DATE) val releaseDate: String?,

    @ColumnInfo(name = FIELD_VOTE_AVERAGE)
    @SerializedName(FIELD_VOTE_AVERAGE) val voteAverage: Float?,

    @ColumnInfo(name = FIELD_BACKDROP_PATH)
    @SerializedName(FIELD_BACKDROP_PATH) val backdropPath: String?,

    @Transient
    @ColumnInfo(name = FIELD_CATEGORY) var category: String?
) : Parcelable {
    constructor(
        id: Int?,
        overview: String?,
        title: String?,
        posterPath: String?,
        originalTitle: String?,
        releaseDate: String?,
        voteAverage: Float?,
        backdropPath: String?,
        category: String?
    ) : this(
        id,
        overview,
        title,
        null,
        posterPath,
        originalTitle,
        releaseDate,
        voteAverage,
        backdropPath,
        category
    )

    companion object {
        const val POPULAR = "popular"
        const val TOP_RATED = "top_rated"
        const val UPCOMING = "upcoming"

        const val FIELD_ID = "id"
        const val FIELD_OVERVIEW = "overview"
        const val FIELD_TITLE = "title"
        const val FIELD_GENRES_ID = "genres_id"
        const val FIELD_POSTER_PATH = "poster_path"
        const val FIELD_ORIGINAL_TITLE = "original_title"
        const val FIELD_RELEASE_DATE = "release_date"
        const val FIELD_VOTE_AVERAGE = "vote_average"
        const val FIELD_BACKDROP_PATH = "backdrop_path"
        const val FIELD_CATEGORY = "category"
    }
}
