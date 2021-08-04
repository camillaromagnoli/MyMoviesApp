package com.example.mymoviesapp.features.filter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mymoviesapp.databinding.ItemGenreBinding
import com.example.mymoviesapp.domain.genre.entity.Genre

class GenresAdapter(
    private val listener: (id: Int) -> Unit
) : RecyclerView.Adapter<GenresAdapter.GenreViewHolder>() {

    private val items: MutableList<Genre> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = GenreViewHolder(
        ItemGenreBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
    )

    override fun onBindViewHolder(holder: GenreViewHolder, position: Int) {
        items.getOrNull(position)?.run {
            holder.bind(
                this,
                listener
            )
        }
    }

    override fun getItemCount(): Int = items.size

    fun addGenres(list: List<Genre>?) {
        items.clear()
        items.addAll(list.orEmpty())
        notifyDataSetChanged()
    }

    inner class GenreViewHolder(private val binding: ItemGenreBinding) :
        RecyclerView.ViewHolder(binding.root) {
        internal fun bind(
            item: Genre,
            listener: (id: Int) -> Unit
        ) {
            binding.genreTextView.text = item.name
            binding.root.setOnClickListener {
                listener.invoke(item.id ?: 0)
            }
        }
    }

}