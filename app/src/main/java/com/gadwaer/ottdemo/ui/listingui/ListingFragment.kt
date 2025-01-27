package com.gadwaer.ottdemo.ui.listingui

import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.gadwaer.ottdemo.R
import com.gadwaer.ottdemo.databinding.FragmentListingBinding
import com.gadwaer.ottdemo.ui.adapters.MoviePagingAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
@AndroidEntryPoint
class ListingFragment : Fragment() {


    private lateinit var binding: FragmentListingBinding
    private lateinit var adapter: MoviePagingAdapter
    private val viewModel: ListingViewModel by viewModels()



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding=FragmentListingBinding.inflate(layoutInflater, container, false)

        setUpMovieList()


        return binding.root
    }

    private fun setUpMovieList() {
        adapter = MoviePagingAdapter()
        binding.rvListing.layoutManager=LinearLayoutManager(requireContext())
        binding.rvListing.adapter = adapter


        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.getMoviesPaging( query = "Batman")
                .collect { pagingData ->
                    adapter.submitData(pagingData)
                }
        }

    }
}