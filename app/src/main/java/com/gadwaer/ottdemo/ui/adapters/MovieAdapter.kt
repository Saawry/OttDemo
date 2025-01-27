package com.gadwaer.ottdemo.ui.adapters


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.gadwaer.ottdemo.databinding.ItemMovieBinding
import com.gadwaer.ottdemo.model.Movie
import com.gadwaer.ottdemo.utils.MovieItemClickListener

class MovieAdapter(private val movies: List<Movie>,private val onClick:MovieItemClickListener) :
    RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {

    inner class MovieViewHolder(private val binding: ItemMovieBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(movie: Movie) {
            binding.tvTitle.text = movie.Title
            Glide.with(binding.root.context)
                .load(movie.Poster)
                .into(binding.ivPoster)
            binding.root.setOnClickListener {
                onClick.onMovieClick(movie)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val binding = ItemMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(movies[position])
    }

    override fun getItemCount(): Int = movies.size
}
