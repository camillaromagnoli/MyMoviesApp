package com.example.mymoviesapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.mymoviesapp.MovieListActivity.Companion.MOVIE
import com.example.mymoviesapp.databinding.ActivityDetailsBinding

class DetailsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailsBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        intent.extras?.let {
            val movie = it[MOVIE] as Movie
            binding.titleTextView.text = movie.title
            binding.overviewTextView.text = movie.description
        }
    }
}