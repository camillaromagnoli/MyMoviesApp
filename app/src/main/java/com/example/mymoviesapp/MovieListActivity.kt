package com.example.mymoviesapp

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mymoviesapp.databinding.ActivityDetailsBinding
import com.example.mymoviesapp.databinding.ActivityMovieListBinding

class MovieListActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMovieListBinding

    private val moviesList = listOf<Movie>(
        Movie(1, "Avatar: A Lenda de Aang", "teste"),
        Movie(2, "Loki", "abc"),
        Movie(3, "Madagascar", "Pinguim")
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMovieListBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        setupAdapter()
    }

    private fun setupAdapter() {
        binding.movieRecyclerView.apply {
            layoutManager = LinearLayoutManager(
                this@MovieListActivity
            )
            adapter = MoviesAdapter(moviesList, ::goToDetailsPage )
        }
    }

    private fun goToDetailsPage(id: Long) {
        val intent = Intent(this, DetailsActivity::class.java).apply {
            putExtra(MOVIE_ID, id)
        }
        startActivity(intent)
    }

    companion object {
        const val MOVIE_ID = "movie_id"
    }

}

