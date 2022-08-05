package com.axichise.movieapp.ui.genres

import com.axichise.movieapp.network.executeAndDeliver
import com.axichise.movieapp.ui.utils.Constants.API_KEY
import com.axichise.movieapp.ui.utils.Constants.LANGUAGE

import retrofit2.Retrofit

class GenreRemoteDataSource(retrofit: Retrofit) {
    private val apiService: GenresAPIService = retrofit.create(GenresAPIService::class.java)
    private var genreMapper = GenreMapper()

    fun getGenres(): List<Genre> {
        return apiService.getGenres(API_KEY, LANGUAGE)
            .executeAndDeliver()
            .genres
            .map { genreMapper.map(it) }
    }
}
