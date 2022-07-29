package com.axichise.movieapp.ui.movies

import com.axichise.movieapp.ui.genres.GenresListResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface MoviesAPIService {
    @GET("discover/movie")
    fun getMovies(
        @Query("api_key") apiKey: String,
        @Query("language") language: String
    ) : Call<MoviesListResponse>
}