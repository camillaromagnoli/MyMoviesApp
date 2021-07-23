package com.example.mymoviesapp

import android.graphics.Movie
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.mymoviesapp.MovieListActivity.Companion.MOVIE_ID
import com.example.mymoviesapp.databinding.ActivityDetailsBinding

class DetailsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailsBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        intent.extras?.let {
            binding.titleTextView.text = it[MOVIE_ID].toString()
        }
    }
}