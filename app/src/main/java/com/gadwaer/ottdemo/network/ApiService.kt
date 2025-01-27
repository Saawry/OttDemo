package com.gadwaer.ottdemo.network

import com.gadwaer.ottdemo.model.MovieResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("/")
    suspend fun getMovies(
        @Query("apikey") apiKey: String,
        @Query("s") query: String
    ): MovieResponse

    @GET("/")
    suspend fun getMovies(
        @Query("apikey") apiKey: String,
        @Query("s") query: String,
        @Query("y") year: String?,
        @Query("type") type: String?
    ): MovieResponse

    @GET("/")
    suspend fun getMovies(
        @Query("apikey") apiKey: String,
        @Query("s") query: String,
        @Query("page") type: Int?
    ): MovieResponse
}