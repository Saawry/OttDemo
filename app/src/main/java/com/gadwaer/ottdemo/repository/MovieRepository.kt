package com.gadwaer.ottdemo.repository

import com.gadwaer.ottdemo.model.MovieResponse
import com.gadwaer.ottdemo.network.ApiService
import javax.inject.Inject


class MovieRepository @Inject constructor(private val apiService: ApiService) {


    suspend fun getBatmanMovies(apiKey: String, query: String): MovieResponse {
        return apiService.getMovies(apiKey, query)
    }
    suspend fun getLatestMovies(apiKey: String, query: String, year: String?, type: String?): MovieResponse {
        return apiService.getMovies(apiKey, query, year, type)
    }
}
