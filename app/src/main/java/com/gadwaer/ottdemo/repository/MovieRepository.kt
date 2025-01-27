package com.gadwaer.ottdemo.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.gadwaer.ottdemo.model.Movie
import com.gadwaer.ottdemo.model.MovieResponse
import com.gadwaer.ottdemo.network.ApiService
import com.gadwaer.ottdemo.utils.MoviePagingSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class MovieRepository @Inject constructor(private val apiService: ApiService) {


    suspend fun getBatmanMovies(apiKey: String, query: String): MovieResponse {
        return apiService.getMovies(apiKey, query)
    }
    suspend fun getLatestMovies(apiKey: String, query: String, year: String?, type: String?): MovieResponse {
        return apiService.getMovies(apiKey, query, year, type)
    }

    fun getMoviesPaging(query: String): Flow<PagingData<Movie>> {
        return Pager(
            config = PagingConfig(
                pageSize = 10,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { MoviePagingSource(apiService, query) }
        ).flow
    }
}
