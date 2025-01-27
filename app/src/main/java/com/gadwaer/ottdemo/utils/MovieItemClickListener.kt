package com.gadwaer.ottdemo.utils

import com.gadwaer.ottdemo.model.Movie


interface MovieItemClickListener {
    fun onMovieClick(movie: Movie)
}
