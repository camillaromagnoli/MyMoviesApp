package com.example.mymoviesapp

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mymoviesapp.databinding.ItemMovieBinding

class MoviesAdapter(
    private val items: List<Movie>?,
    private val listener: (id: Long) -> Unit
) : RecyclerView.Adapter<MoviesAdapter.MoviesViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = MoviesViewHolder(
        ItemMovieBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
    )

    override fun onBindViewHolder(holder: MoviesViewHolder, position: Int) {
        items?.getOrNull(position)?.run {
            holder.bind(this, listener)
        }
    }

    override fun getItemCount(): Int = items.orEmpty().size

    inner class MoviesViewHolder(private val binding: ItemMovieBinding) :
        RecyclerView.ViewHolder(binding.root) {
        internal fun bind(item: Movie, listener: (id: Long) -> Unit) {
            binding.apply {
                root.setOnClickListener { listener.invoke(item.id) }
                movieTitle.text = item.title
            }
        }
    }
}