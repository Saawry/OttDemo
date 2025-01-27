package com.gadwaer.ottdemo.utils


import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.gadwaer.ottdemo.BuildConfig
import com.gadwaer.ottdemo.model.Movie
import com.gadwaer.ottdemo.network.ApiService
import javax.inject.Inject

class MoviePagingSource @Inject constructor(
    private val apiService: ApiService,
    private val query: String
) : PagingSource<Int, Movie>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Movie> {
        val page = params.key ?: 1 // Default to the first page if no key is provided.

        return try {
            val response = apiService.getMovies(BuildConfig.API_KEY, query, page)
            val movies = response.Search ?: emptyList()
            val nextKey = if (movies.isEmpty()) null else page + 1

            LoadResult.Page(
                data = movies,
                prevKey = if (page == 1) null else page - 1,
                nextKey = nextKey
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Movie>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }
}
