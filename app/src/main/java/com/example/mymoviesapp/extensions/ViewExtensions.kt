package com.example.mymoviesapp.extensions

import android.view.View
import android.widget.ImageView
import com.bumptech.glide.Glide

fun ImageView.loadImage(view: View?, url: String?) {
    view?.let {
        Glide.with(view)
            .load(url)
            .into(this)
    }
}

fun View.hide() {
    this.visibility = View.GONE
}

fun View.show() {
    this.visibility = View.VISIBLE
}