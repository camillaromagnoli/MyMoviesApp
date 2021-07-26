package com.example.mymoviesapp

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Movie(val id: Long, val title: String, val description: String): Parcelable