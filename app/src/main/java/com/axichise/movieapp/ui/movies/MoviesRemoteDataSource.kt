package com.axichise.movieapp.ui.movies

import android.app.SearchManager
import com.axichise.movieapp.network.executeAndDeliver
import com.axichise.movieapp.ui.utils.Constants.API_KEY
import com.axichise.movieapp.ui.utils.Constants.LANGUAGE
import retrofit2.Retrofit

class MoviesRemoteDataSource (retrofit: Retrofit) {
    private val apiService: MoviesAPIService = retrofit.create(MoviesAPIService::class.java)
    private val movieMapper = MoviesMapper()

    fun getMovies(): List<Movies> {
        return apiService.getMovies(API_KEY, LANGUAGE, SearchManager.QUERY)
            .executeAndDeliver()
            .movies
            .map { movieMapper.map(it) }
    }
}