package com.gadwaer.ottdemo.ui.listingui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.gadwaer.ottdemo.model.Movie
import com.gadwaer.ottdemo.repository.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
@HiltViewModel
class ListingViewModel @Inject constructor(private val repository: MovieRepository) : ViewModel() {

    fun getMoviesPaging(query: String): Flow<PagingData<Movie>> {
        return repository.getMoviesPaging(query).cachedIn(viewModelScope)
    }
}