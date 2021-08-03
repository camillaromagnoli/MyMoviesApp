package com.example.mymoviesapp.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.mymoviesapp.databinding.ActivityDetailsBinding
import com.example.mymoviesapp.extensions.loadImage
import com.example.mymoviesapp.domain.movie.entity.Movie
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        intent.extras?.let {
            val movie = it[MOVIE] as Movie
            binding.contentInfoInclude.titleTextView.text = movie.title
            binding.contentInfoInclude.originalTitleTextView.text = movie.originalTitle
            binding.contentInfoInclude.overviewTextView.text = movie.overview
            binding.contentInfoInclude.releaseDateTextView.text = movie.releaseDate
            binding.contentInfoInclude.voteAverageTextView.text = movie.voteAverage.toString()
            binding.contentInfoInclude.voteAverageRatingBar.rating = (movie.voteAverage ?: 0f) / 2
            binding.backdropImageView.loadImage(binding.root, "$BACKDROP_BASE_URL${movie.backdropPath}")
        }
    }
    companion object {
        const val MOVIE = "movie"
        private const val BACKDROP_BASE_URL = "https://image.tmdb.org/t/p/original/"
    }
}