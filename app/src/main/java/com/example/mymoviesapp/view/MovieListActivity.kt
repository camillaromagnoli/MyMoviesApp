package com.example.mymoviesapp.view

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.mymoviesapp.databinding.ActivityMovieListBinding
import com.example.mymoviesapp.domain.Movie
import com.example.mymoviesapp.view.DetailsActivity.Companion.MOVIE
import com.example.mymoviesapp.viewmodel.MovieViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieListActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMovieListBinding
    private val movieListViewModel: MovieViewModel by viewModels()

    private val adapterMovies by lazy { MoviesAdapter(::goToDetailsPage) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMovieListBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupAdapter()
        setupObservers()
        movieListViewModel.getMovieData()
    }

    private fun setupAdapter() {
        binding.movieRecyclerView.adapter = adapterMovies
    }

    private fun setupObservers() {
        movieListViewModel.getMovies().observe(this, {
            adapterMovies.addMovies(it)
        })
    }

    private fun goToDetailsPage(movie: Movie) {
        val intent = Intent(this, DetailsActivity::class.java).apply {
            putExtra(MOVIE, movie)
        }
        startActivity(intent)
    }
}