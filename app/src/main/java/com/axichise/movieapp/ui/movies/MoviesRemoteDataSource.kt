package com.axichise.movieapp.ui.movies

import com.axichise.movieapp.network.executeAndDeliver
import com.axichise.movieapp.ui.movieDetails.MovieDetails
import com.axichise.movieapp.ui.movieDetails.MovieDetailsMapper
import com.axichise.movieapp.ui.utils.Constants
import com.axichise.movieapp.ui.utils.Constants.API_KEY
import com.axichise.movieapp.ui.utils.Constants.LANGUAGE
import retrofit2.Retrofit

class MoviesRemoteDataSource(retrofit: Retrofit) {
    private val apiService: MoviesAPIService = retrofit.create(MoviesAPIService::class.java)
    private val movieMapper = MoviesMapper()
    private val movieDetailsMapper = MovieDetailsMapper()

    fun getMovies(withCast: String, withGenres: String): List<Movies> {
        return apiService.getMovies(API_KEY, LANGUAGE, withCast, withGenres)
            .executeAndDeliver()
            .movies
            .map { movieMapper.map(it) }
    }

    fun getSearchedMovie(query: String): List<Movies> {
        return apiService.getSearchedMovie(API_KEY, LANGUAGE, query)
            .executeAndDeliver()
            .movies
            .map { movieMapper.map(it) }
    }

    fun getMovieDetails(movieId: Int): MovieDetails {
        return apiService.getmovieDeatils(
            movieId,
            Constants.API_KEY,
            Constants.LANGUAGE,
            Constants.APPEND_TO_RESPONSE
        )
            .executeAndDeliver()
            .let { movieDetailsMapper.map(it) }
    }
}