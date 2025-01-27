package com.gadwaer.ottdemo.ui.homeui

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.gadwaer.ottdemo.databinding.FragmentHomeBinding
import com.gadwaer.ottdemo.model.Movie
import com.gadwaer.ottdemo.ui.adapters.MovieAdapter
import com.gadwaer.ottdemo.utils.MovieItemClickListener
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.imaginativeworld.whynotimagecarousel.listener.CarouselListener
import org.imaginativeworld.whynotimagecarousel.model.CarouselItem

@AndroidEntryPoint
class HomeFragment : Fragment() {


    private val viewModel: HomeViewModel by viewModels()
    private lateinit var binding: FragmentHomeBinding
    private lateinit var batmanMovieAdapter: MovieAdapter
    private lateinit var latestMovieAdapter: MovieAdapter



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)

        setUpCarousel()
        setUpBatmanMovie()
        setUpLatestMovie()

        return binding.root
    }

    private fun setUpCarousel() {

        binding.carousel.registerLifecycle(lifecycle)

        val list = mutableListOf<CarouselItem>()
        // Image URL with caption

        list.add(
            CarouselItem(
                imageUrl = "https://blogger.googleusercontent.com/img/b/R29vZ2xl/AVvXsEjJzmTJfldazoU8ElPC8ljF1otOqVACdjSgtgHinzMOs1athrMZzdVWDSu0UUz4DwhpGa64DM7ANeKZrHW2fZR9OEm1MShhWdqE9yONo2eCMGe8AT2KVBOruu2jjEyN7LyV0h9wp2QwyFXU/s1600/DC+Comics%25E2%2580%2599+Aquaman+Final+Theatrical+One+Sheet+Movie+Posters+%2526+Banners+%25283%2529.jpg",
                caption = "Aquaman"
            )
        )
        list.add(
            CarouselItem(
                imageUrl = "https://dunenewsnet.com/wp-content/uploads/2021/08/Dune-Movie-Official-Poster-banner-feature.jpg",
                caption = "DUNE"
            )
        )

        list.add(
            CarouselItem(
                imageUrl = "https://collider.com/wp-content/uploads/the-avengers-movie-poster-banners-04.jpg",
                caption = "Avengers"
            )
        )

// Just image URL
        list.add(
            CarouselItem(
                imageUrl = "https://collider.com/wp-content/uploads/dark-knight-rises-movie-poster-banner-catwoman.jpg",
                caption = "The Dark Knight Rises"
            )
        )


        list.add(
            CarouselItem(
                imageUrl = "https://collider.com/wp-content/uploads/inception_movie_poster_banner_01.jpg",
                caption = "Inception"
            )
        )



        binding.carousel.setData(list)

        binding.carousel.carouselListener = object : CarouselListener {

            override fun onClick(position: Int, carouselItem: CarouselItem) {
                Toast.makeText(requireContext(), carouselItem.caption, Toast.LENGTH_SHORT).show()
            }

        }
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun setUpBatmanMovie() {
        binding.rvBatman.setLayoutManager( LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL,false))

        // Fetch movies with query "Batman"
        viewModel.fetchBatmanMovies("Batman")

        lifecycleScope.launch {
            viewModel.batmanMovies.collectLatest { movies ->
                batmanMovieAdapter = MovieAdapter(movies,object:MovieItemClickListener{
                    override fun onMovieClick(movie: Movie) {
                        onMovieClick(movie)
                    }

                })
                binding.rvBatman.adapter = batmanMovieAdapter

                batmanMovieAdapter.notifyDataSetChanged()
            }
        }
    }
    @SuppressLint("NotifyDataSetChanged")
    private fun setUpLatestMovie() {
        binding.rvLatest.setLayoutManager( LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL,false))

        // Fetch movies with query "Batman"
        viewModel.fetchLatestMovies("Batman","2022","movie")

        lifecycleScope.launch {
            viewModel.latestMovies.collectLatest { movies ->
                //Toast.makeText(requireContext(), movies.size, Toast.LENGTH_SHORT).show()
                latestMovieAdapter = MovieAdapter(movies,object:MovieItemClickListener{
                    override fun onMovieClick(movie: Movie) {
                        onMovieClick(movie)
                    }

                })
                binding.rvLatest.adapter = latestMovieAdapter

                latestMovieAdapter.notifyDataSetChanged()
            }
        }
    }

    private fun onMovieClick(movie: Movie) {
        // Handle movie click
        Toast.makeText(requireContext(), "Clicked: ${movie.Title}", Toast.LENGTH_SHORT).show()

        // Navigate to detail screen or perform any other action
    }
}