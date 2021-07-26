package com.example.mymoviesapp

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mymoviesapp.databinding.ItemMovieBinding

class MoviesAdapter(
    private val items: List<Movie>?, //lista de filmes
    private val listener: (movie: Movie) -> Unit //funcao do click
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
            holder.bind(this, listener) //pega a lista de itens se nao estiver nula em cada posicao e escuta o bind
        }
    }

    override fun getItemCount(): Int = items.orEmpty().size

    inner class MoviesViewHolder(private val binding: ItemMovieBinding) : //classe da view
        RecyclerView.ViewHolder(binding.root) {
        internal fun bind(item: Movie, listener: (movie: Movie) -> Unit) { //listener retorna uma funcao que recebe movie como parametro
            binding.apply { //o binding é o item_movie, quando clicado é aberto
                root.setOnClickListener { listener.invoke(item) } //invoca o item clicado pela raiz
                movieTitle.text = item.title
            }
        }
    }
}