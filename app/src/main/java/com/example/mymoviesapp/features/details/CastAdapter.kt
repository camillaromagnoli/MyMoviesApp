package com.example.mymoviesapp.features.details

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mymoviesapp.data.core.ClientBuilder
import com.example.mymoviesapp.databinding.ItemCastBinding
import com.example.mymoviesapp.domain.credits.entity.Cast
import com.example.mymoviesapp.extensions.loadImage

class CastAdapter : RecyclerView.Adapter<CastAdapter.CastViewHolder>() {

    private val items: MutableList<Cast> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = CastViewHolder(
        ItemCastBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
    )

    override fun onBindViewHolder(holder: CastViewHolder, position: Int) {
        items.getOrNull(position)?.run {
            holder.bind(this)
        }
    }

    override fun getItemCount(): Int = items.size

    fun addCast(list: List<Cast>?) {
        items.clear()
        items.addAll(list.orEmpty())
        notifyDataSetChanged()
    }

    inner class CastViewHolder(private val binding: ItemCastBinding) :
        RecyclerView.ViewHolder(binding.root) {
        internal fun bind(item: Cast) {
            binding.apply {
                castPictureImageView.loadImage(
                    root,
                    "${ClientBuilder.BASE_POSTER_URL}${item.profilePath}"
                )
                castNameTextView.text = item.character
                castRealNameTextView.text = item.name
            }
        }
    }

}