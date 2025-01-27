package com.gadwaer.ottdemo.ui.activity

import android.net.Uri
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.gadwaer.ottdemo.R
import com.gadwaer.ottdemo.databinding.ActivityDetailsBinding
import com.gadwaer.ottdemo.model.Movie
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }


// Receive the Movie object from the Intent
        val movie = intent.getParcelableExtra<Movie>("movie")

        if (movie != null) {
            // Set Movie Details binding.tvTitle.text = movie.Title
            binding.tvTitle.text = "Title: ${movie.Title}"
            binding.tvYear.text = "Year: ${movie.Year}"
            binding.tvImdbID.text = "IMDb ID: ${movie.imdbID}"
            binding.tvType.text = "Type: ${movie.Type}"


            // Set up VideoView
            setupVideoView()
        }

    }

    private fun setupVideoView() {
        // Set video URI
        val videoUri =
            Uri.parse("https://bitmovina.akamaihd.net/content/MI201109210084_1/mpds/f08e80da-bf1d-4e3d8899-f0f6155f6efa.mpd")
        binding.videoView.setVideoURI(videoUri)

        // Enable media controls
        binding.videoView.setMediaController(android.widget.MediaController(this))
        binding.videoView.start()
    }
}