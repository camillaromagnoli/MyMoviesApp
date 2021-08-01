package com.example.mymoviesapp.view

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.example.mymoviesapp.databinding.ActivityMovieListBinding
import com.example.mymoviesapp.models.Movie
import com.example.mymoviesapp.view.DetailsActivity.Companion.MOVIE
import com.example.mymoviesapp.viewmodel.MovieViewModel

class MovieListActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMovieListBinding
    private val movieViewModel = MovieViewModel()

    private val adapterMovies by lazy { MoviesAdapter(::goToDetailsPage) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMovieListBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupAdapter()
        setupObservers()
        movieViewModel.getMovieData()
    }

    private fun setupAdapter() {
        binding.movieRecyclerView.adapter = adapterMovies
    }

    private fun setupObservers() {
        movieViewModel.getMovies().observe(this, {
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