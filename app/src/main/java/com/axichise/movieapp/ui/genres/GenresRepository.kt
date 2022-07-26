package com.axichise.movieapp.ui.genres

import com.axichise.movieapp.database.Database
import com.axichise.movieapp.network.APIClient

class GenresRepository private constructor(){
    companion object {
        val instance = GenresRepository()
    }

    private val genresRemoteDataSource = GenreRemoteDataSource(APIClient.instance.retrofit)
    private val genreLocalDataSource = GenresLocalDataSource(Database.instance)
   // fun getAllLocalIds() = genreLocalDataSource.getAllIds()
    fun getAllRemoteGenres() = genresRemoteDataSource.getGenres()
    fun getAllLocalGenres() = genreLocalDataSource.getAll()
    fun saveLocal(genre: Genre) = genreLocalDataSource.save(genre)
    fun saveAllLocal(genres: List<Genre>) = genreLocalDataSource.saveAll(genres)
    fun deleteLocal(genre: Genre) = genreLocalDataSource.delete(genre)
    fun deleteAllLocal() = genreLocalDataSource.deleteAll()
    fun deleteAllLocal(genres: List<Genre>) = genreLocalDataSource.deleteAll(genres)
    fun replaceAllLocal(genres: List<Genre>) = genreLocalDataSource.replaceAll(genres)
    fun getCount() = genreLocalDataSource.getCount()
}