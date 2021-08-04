package com.example.mymoviesapp.features.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mymoviesapp.data.core.ClientBuilder
import com.example.mymoviesapp.databinding.ItemMoviePostersBinding
import com.example.mymoviesapp.domain.movie.entity.Movie
import com.example.mymoviesapp.extensions.loadImage

class MoviesPosterAdapter(
    private val listener: (movie: Movie) -> Unit
) : RecyclerView.Adapter<MoviesPosterAdapter.MoviesPosterViewHolder>() {

    private val items: MutableList<Movie> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = MoviesPosterViewHolder(
        ItemMoviePostersBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
    )

    override fun onBindViewHolder(holder: MoviesPosterViewHolder, position: Int) {
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

    inner class MoviesPosterViewHolder(private val binding: ItemMoviePostersBinding) :
        RecyclerView.ViewHolder(binding.root) {
        internal fun bind(
            item: Movie,
            listener: (movie: Movie) -> Unit
        ) {
            binding.apply {
                root.setOnClickListener { listener.invoke(item) }
                posterImageView.loadImage(
                    root,
                    "${ClientBuilder.BASE_POSTER_URL}${item.posterPath}"
                )
            }
        }
    }

}