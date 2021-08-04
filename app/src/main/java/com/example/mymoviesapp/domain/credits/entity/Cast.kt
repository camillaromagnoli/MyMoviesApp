package com.example.mymoviesapp.domain.credits.entity

import com.google.gson.annotations.SerializedName

data class Cast(
    val name: String,
    val character: String,
    @SerializedName("profile_path") val profilePath: String
)