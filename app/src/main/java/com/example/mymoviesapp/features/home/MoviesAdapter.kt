package com.example.mymoviesapp.features.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mymoviesapp.data.core.ClientBuilder.Companion.BASE_POSTER_URL
import com.example.mymoviesapp.databinding.ItemMovieBinding
import com.example.mymoviesapp.extensions.loadImage
import com.example.mymoviesapp.domain.movie.entity.Movie

class MoviesAdapter(
    private val listener: (movie: Movie) -> Unit
) : RecyclerView.Adapter<MoviesAdapter.MoviesViewHolder>() {

    private val items: MutableList<Movie> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = MoviesViewHolder(
        ItemMovieBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
    )

    override fun onBindViewHolder(holder: MoviesViewHolder, position: Int) {
        items.getOrNull(position)?.run {
            holder.bind(
                this,
                listener
            )
        }
    }

    override fun getItemCount(): Int = items.size

    fun addMovies(list: List<Movie>?) {
        items.clear()
        items.addAll(list.orEmpty())
        notifyDataSetChanged()
    }

    inner class MoviesViewHolder(private val binding: ItemMovieBinding) :
        RecyclerView.ViewHolder(binding.root) {
        internal fun bind(
            item: Movie,
            listener: (movie: Movie) -> Unit
        ) {
            binding.apply {
                root.setOnClickListener { listener.invoke(item) }
                movieTitleTextView.text = item.title
                originalTitleTextView.text = item.originalTitle
                releaseDateTextView.text = "Release Date: ${item.releaseDate}"
                voteAverageTextView.text = item.voteAverage.toString()
                voteAverageRatingBar.rating = (item.voteAverage ?: 0f) / 2
                posterImageView.loadImage(root, "$BASE_POSTER_URL${item.posterPath}")
            }
        }
    }

}