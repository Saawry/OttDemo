package com.gadwaer.ottdemo

import com.gadwaer.ottdemo.network.ApiService
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class MovieApiTest {

    private lateinit var mockWebServer: MockWebServer
    private lateinit var api: ApiService

    @Before
    fun setup() {
        mockWebServer = MockWebServer()
        mockWebServer.start()

        // Create a Retrofit instance pointing to the MockWebServer
        api = Retrofit.Builder()
            .baseUrl(mockWebServer.url("/")) // Use the mock server URL
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }

    @After
    fun tearDown() {
        mockWebServer.shutdown()
    }

    @Test
    fun `searchMovies returns valid data`() = runBlocking {
        // Mock response
        val mockResponse = """
            {
                "Search": [
                    {
                        "Title": "Batman Begins",
                        "Year": "2005",
                        "imdbID": "tt0372784",
                        "Type": "movie",
                        "Poster": "https://example.com/batman.jpg"
                    }
                ]
            }
        """.trimIndent()

        mockWebServer.enqueue(
            MockResponse()
                .setResponseCode(200)
                .setBody(mockResponse)
        )

        // Make the API call
        val response = api.getMovies(BuildConfig.API_KEY, "Batman")

        // Assertions
        assertEquals(1, response.Search.size)
        assertEquals("Batman Begins", response.Search[0].Title)
        assertEquals("2005", response.Search[0].Year)
    }


}
