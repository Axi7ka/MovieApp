package com.axichise.movieapp.ui.movies

import com.axichise.movieapp.ui.movieDetails.MovieDetails
import com.axichise.movieapp.ui.movieDetails.MovieDetailsResponse
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MoviesAPIService {

    @GET("discover/movie")
    fun getMovies(
        @Query("api_key") apiKey: String,
        @Query("language") language: String,
        @Query("with_cast") withCast: String,
        @Query("with_genres") withGenres: String
    ): Call<MoviesListResponse>

    @GET("search/movie")
    fun getSearchedMovie(
        @Query("api_key") apiKey: String,
        @Query("language") language: String,
        @Query("query") query: String
    ) : Call<MoviesListResponse>

    @GET("movie/{movie_id}")
    fun getmovieDeatils(
        @Path("movie_id") movieId : Int,
        @Query("api_key") apiKey: String,
        @Query("language") language: String,
        @Query("append_to_response") appendToResponse: String
        ) : Call<MovieDetailsResponse>
}