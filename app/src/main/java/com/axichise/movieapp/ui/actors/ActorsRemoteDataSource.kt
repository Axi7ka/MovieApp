package com.axichise.movieapp.ui.actors

import com.axichise.movieapp.network.executeAndDeliver
import com.axichise.movieapp.ui.genres.Genre
import com.axichise.movieapp.ui.genres.GenreMapper
import com.axichise.movieapp.ui.genres.GenresAPIService
import com.axichise.movieapp.ui.utils.Constants.API_KEY
import com.axichise.movieapp.ui.utils.Constants.LANGUAGE

import retrofit2.Retrofit

class ActorsRemoteDataSource (retrofit: Retrofit) {
    private val apiService: ActorsApiService = retrofit.create(ActorsApiService::class.java)
    private var actorsMapper = ActorsMapper()

    fun getActors(): List<Actors> {
        return apiService.getActors(API_KEY, LANGUAGE)
            .executeAndDeliver()
            .actors
            .map { actorsMapper.map(it) }
    }
}
