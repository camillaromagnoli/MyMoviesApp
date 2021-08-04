package com.example.mymoviesapp.features.details

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mymoviesapp.data.core.ClientBuilder.Companion.BACKDROP_BASE_URL
import com.example.mymoviesapp.databinding.ActivityDetailsBinding
import com.example.mymoviesapp.extensions.loadImage
import com.example.mymoviesapp.domain.movie.entity.Movie
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailsBinding

    private val detailsViewModel: DetailsViewModel by viewModels()

    private val castAdapter by lazy { CastAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupView()
        setupListeners()
        setupAdapter()
        setupObservers()
    }

    private fun setupAdapter() {
        binding.contentInfoInclude.castRecyclerView.apply {
            adapter = castAdapter
            layoutManager = LinearLayoutManager(this@DetailsActivity, LinearLayoutManager.HORIZONTAL, false)
        }
    }

    private fun setupObservers() {
        detailsViewModel.getCast().observe(this, {
            castAdapter.addCast(it)
        })
    }

    private fun setupListeners() {
        binding.returnImageButton.setOnClickListener {
            finish()
        }
    }

    private fun setupView() {
        intent.extras?.let {
            val movie = it[MOVIE] as Movie
            binding.contentInfoInclude.titleTextView.text = movie.title
            binding.contentInfoInclude.originalTitleTextView.text = movie.originalTitle
            binding.contentInfoInclude.overviewTextView.text = movie.overview
            binding.contentInfoInclude.releaseDateTextView.text = movie.releaseDate
            binding.contentInfoInclude.voteAverageTextView.text = movie.voteAverage.toString()
            binding.contentInfoInclude.voteAverageRatingBar.rating = (movie.voteAverage ?: 0f) / 2
            binding.backdropImageView.loadImage(binding.root, "$BACKDROP_BASE_URL${movie.backdropPath}")
            detailsViewModel.getCredits(movie.id)
        }
    }

    companion object {
        const val MOVIE = "movie"
    }
}