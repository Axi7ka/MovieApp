package com.axichise.movieapp.ui.movies

import com.axichise.movieapp.database.Database
import com.axichise.movieapp.network.APIClient

class MoviesRepository private constructor() {
    companion object {
        val instance = MoviesRepository()
    }

    private val moviesRemoteDataSource = MoviesRemoteDataSource(APIClient.instance.retrofit)
    private val moviesLocalDataSource = MoviesLocalDataSource(Database.instance)
    fun getAllSearchedMovies(query: String): List<Movies> {
        var searchedMovie: List<Movies> = moviesRemoteDataSource.getSearchedMovie(query)
        return searchedMovie
    }

    fun getAllRemoteMovies() = moviesRemoteDataSource.getMovies()
    fun getAllLocalMovies() = moviesLocalDataSource.getAll()
    fun saveLocal(movie: Movies) = moviesLocalDataSource.save(movie)
    fun saveAllLocal(movies: List<Movies>) = moviesLocalDataSource.saveAll(movies)
    fun deleteLocal(movie: Movies) = moviesLocalDataSource.delete(movie)
    fun deleteAllLocal() = moviesLocalDataSource.deleteAll()
    fun deleteAllLocal(movies: List<Movies>) = moviesLocalDataSource.deleteAll(movies)
    fun replaceAllLocal(movies: List<Movies>) = moviesLocalDataSource.replaceAll(movies)
    fun getCount() = moviesLocalDataSource.getCount()
}