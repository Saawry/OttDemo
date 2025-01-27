package com.gadwaer.ottdemo.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Movie(
    val Title: String,
    val Year: String,
    val imdbID: String,
    val Type: String,
    val Poster: String
): Parcelable

data class MovieResponse(
    val Search: List<Movie>,
    val totalResults: String,
    val Response: String
)
