package com.example.mymoviesapp.features.details

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mymoviesapp.data.core.ClientBuilder.Companion.BACKDROP_BASE_URL
import com.example.mymoviesapp.databinding.ActivityDetailsBinding
import com.example.mymoviesapp.domain.movie.entity.Movie
import com.example.mymoviesapp.extensions.*
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
            layoutManager =
                LinearLayoutManager(this@DetailsActivity, LinearLayoutManager.HORIZONTAL, false)
        }
    }

    private fun setupObservers() {
        detailsViewModel.getCastMembersState().observe(this, { state ->
            state?.handleState(
                loading = { binding.progress.show() },
                stopLoading = { binding.progress.hide() },
                success = { castAdapter.addCast(it) },
                error = { showErrorModal(it) { requestCredits() } }
            )
        })
    }

    private fun setupListeners() {
        binding.returnImageButton.setOnClickListener {
            finish()
        }
    }

    private fun setupView() {
        intent.extras?.let {
            detailsViewModel.movie = it[MOVIE] as Movie
            binding.contentInfoInclude.titleTextView.text = detailsViewModel.movie?.title
            binding.contentInfoInclude.originalTitleTextView.text =
                detailsViewModel.movie?.originalTitle
            binding.contentInfoInclude.overviewTextView.text = detailsViewModel.movie?.overview
            binding.contentInfoInclude.releaseDateTextView.text =
                detailsViewModel.movie?.releaseDate
            binding.contentInfoInclude.voteAverageTextView.text =
                detailsViewModel.movie?.voteAverage.toString()
            binding.contentInfoInclude.voteAverageRatingBar.rating =
                (detailsViewModel.movie?.voteAverage ?: 0f) / 2
            binding.backdropImageView.loadImage(
                binding.root,
                "$BACKDROP_BASE_URL${detailsViewModel.movie?.backdropPath}"
            )
            requestCredits()
        }
    }

    private fun requestCredits() {
        detailsViewModel.getCredits()
    }

    companion object {
        const val MOVIE = "movie"
    }
}