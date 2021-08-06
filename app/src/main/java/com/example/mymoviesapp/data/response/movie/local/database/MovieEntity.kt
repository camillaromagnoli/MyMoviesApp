package com.example.mymoviesapp.data.response.movie.local.database


import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.mymoviesapp.data.response.Mapper
import com.example.mymoviesapp.domain.movie.entity.Movie

@Entity
data class MovieEntity(
    @PrimaryKey
    @ColumnInfo(name = FIELD_ID) var id: Int? = null,
    @ColumnInfo(name = FIELD_OVERVIEW) var overview: String? = null,
    @ColumnInfo(name = FIELD_TITLE) var title: String? = null,
    @ColumnInfo(name = FIELD_POSTER_PATH) var posterPath: String? = null,
    @ColumnInfo(name = FIELD_ORIGINAL_TITLE) var originalTitle: String? = null,
    @ColumnInfo(name = FIELD_RELEASE_DATE) var releaseDate: String? = null,
    @ColumnInfo(name = FIELD_VOTE_AVERAGE) var voteAverage: Float? = null,
    @ColumnInfo(name = FIELD_BACKDROP_PATH) var backdropPath: String? = null,
    @ColumnInfo(name = FIELD_CATEGORY) var category: String? = null
) : Mapper<Movie, MovieEntity> {
    companion object {
        const val POPULAR = "popular"
        const val TOP_RATED = "top_rated"
        const val UPCOMING = "upcoming"

        const val FIELD_ID = "id"
        const val FIELD_OVERVIEW = "overview"
        const val FIELD_TITLE = "title"
        const val FIELD_GENRE_IDS = "genre_ids"
        const val FIELD_POSTER_PATH = "poster_path"
        const val FIELD_ORIGINAL_TITLE = "original_title"
        const val FIELD_RELEASE_DATE = "release_date"
        const val FIELD_VOTE_AVERAGE = "vote_average"
        const val FIELD_BACKDROP_PATH = "backdrop_path"
        const val FIELD_CATEGORY = "category"
    }

    override fun toDomain(): Movie {
        return Movie(
            id,
            overview,
            title,
            null,
            posterPath,
            originalTitle,
            releaseDate,
            voteAverage,
            backdropPath
        )
    }

    override fun toEntity(domain: Movie): MovieEntity {
        return apply {
            id = domain.id
            overview = domain.overview
            title = domain.title
            posterPath = domain.posterPath
            originalTitle = domain.originalTitle
            releaseDate = domain.releaseDate
            voteAverage = domain.voteAverage
            backdropPath = domain.backdropPath
        }
    }

    fun withCategory(category: String) = apply {
        this.category = category
    }
}
