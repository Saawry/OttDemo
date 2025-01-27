package com.gadwaer.ottdemo.ui.homeui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gadwaer.ottdemo.BuildConfig
import com.gadwaer.ottdemo.model.Movie
import com.gadwaer.ottdemo.repository.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val repository: MovieRepository) : ViewModel() {

    private val _batmanMovies = MutableStateFlow<List<Movie>>(emptyList())
    val batmanMovies: StateFlow<List<Movie>> get() = _batmanMovies


    private val _latestMovies = MutableStateFlow<List<Movie>>(emptyList())
    val latestMovies: StateFlow<List<Movie>> get() = _latestMovies

    private val apiKey = BuildConfig.API_KEY

    fun fetchBatmanMovies(query: String) {
        viewModelScope.launch {
            try {
                val response = repository.getBatmanMovies(apiKey, query)
                if (response.Response == "True") {
                    _batmanMovies.value = response.Search
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun fetchLatestMovies(query: String, year: String? = null, type: String? = null) {
        viewModelScope.launch {
            try {
                val response = repository.getLatestMovies(apiKey, query, year, type)
                if (response.Response == "True") {
                    _latestMovies.value = response.Search
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}