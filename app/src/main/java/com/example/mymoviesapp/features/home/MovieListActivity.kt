package com.example.mymoviesapp.features.home

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mymoviesapp.base.BottomSheetEvents
import com.example.mymoviesapp.databinding.ActivityMovieListBinding
import com.example.mymoviesapp.domain.movie.entity.Movie
import com.example.mymoviesapp.features.details.DetailsActivity
import com.example.mymoviesapp.features.details.DetailsActivity.Companion.MOVIE
import com.example.mymoviesapp.features.filter.FilterBottomSheetFragment
import com.example.mymoviesapp.features.filter.FilterBottomSheetFragment.Companion.FILTER_BOTTOM_SHEET_FRAGMENT
import com.example.mymoviesapp.features.filter.FilterBottomSheetFragment.Companion.ID
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieListActivity : AppCompatActivity(), BottomSheetEvents {
    private lateinit var binding: ActivityMovieListBinding
    private val movieListViewModel: MovieViewModel by viewModels()

    private val popularMoviesAdapter by lazy { MoviesAdapter(::goToDetailsPage) }
    private val upcomingMoviesAdapter by lazy { MoviesPosterAdapter(::goToDetailsPage) }
    private val topRatedMoviesAdapter by lazy { MoviesPosterAdapter(::goToDetailsPage) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMovieListBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupAdapter()
        setupObservers()
        setupListeners()
        movieListViewModel.getMovieData()
    }

    private fun setupListeners() {
        binding.filterImageButton.setOnClickListener {
            openFilterBottomSheet()
        }
    }

    private fun openFilterBottomSheet() {
        val bottomSheet = FilterBottomSheetFragment.newInstance()
        if (supportFragmentManager.findFragmentByTag(FILTER_BOTTOM_SHEET_FRAGMENT) == null) {
            bottomSheet.show(supportFragmentManager, FILTER_BOTTOM_SHEET_FRAGMENT)
        }
    }

    private fun setupAdapter() {
        binding.popularMovieRecyclerView.adapter = popularMoviesAdapter
        binding.upcomingMovieRecyclerView.apply {
            adapter = upcomingMoviesAdapter
            layoutManager =
                LinearLayoutManager(this@MovieListActivity, LinearLayoutManager.HORIZONTAL, false);
        }
        binding.topRatedMovieRecyclerView.apply {
            adapter = topRatedMoviesAdapter
            layoutManager =
                LinearLayoutManager(this@MovieListActivity, LinearLayoutManager.HORIZONTAL, false);
        }
    }

    private fun setupObservers() {
        movieListViewModel.getPopularMovies().observe(this, {
            popularMoviesAdapter.addMovies(it)
        })
        movieListViewModel.getTopRatedMovies().observe(this, {
            topRatedMoviesAdapter.addMovies(it)
        })
        movieListViewModel.getUpcomingMovies().observe(this, {
            upcomingMoviesAdapter.addMovies(it)
        })
    }

    private fun goToDetailsPage(movie: Movie) {
        val intent = Intent(this, DetailsActivity::class.java).apply {
            putExtra(MOVIE, movie)
        }
        startActivity(intent)
    }

    override fun onBottomSheetClose(args: Map<String, Any>) {
        args[ID]?.let {
            popularMoviesAdapter.addMovies(
                movieListViewModel.filterByGenre(
                    it as Int,
                    movieListViewModel.getPopularMovies().value
                )
            )
            topRatedMoviesAdapter.addMovies(
                movieListViewModel.filterByGenre(
                    it,
                    movieListViewModel.getTopRatedMovies().value
                )
            )
            upcomingMoviesAdapter.addMovies(
                movieListViewModel.filterByGenre(
                    it,
                    movieListViewModel.getUpcomingMovies().value
                )
            )
        } ?: run {
                popularMoviesAdapter.addMovies(movieListViewModel.getPopularMovies().value)
                topRatedMoviesAdapter.addMovies(movieListViewModel.getTopRatedMovies().value)
                upcomingMoviesAdapter.addMovies(movieListViewModel.getUpcomingMovies().value)
        }
    }
}