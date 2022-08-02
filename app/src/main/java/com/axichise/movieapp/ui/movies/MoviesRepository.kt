package com.axichise.movieapp.ui.movies

import android.graphics.Movie
import com.axichise.movieapp.database.Database
import com.axichise.movieapp.network.APIClient

class MoviesRepository private constructor() {
    companion object {
        val instance = MoviesRepository()
    }

    private val moviesRemoteDataSource = MoviesRemoteDataSource(APIClient.instance.retrofit)
    private val moviesLocalDataSource = MoviesLocalDataSource(Database.instance)

    fun getAllSearchedMovies(query: String)= moviesRemoteDataSource.getSearchedMovie(query)

    fun getAllRemoteMovies(withCast: String, withGenres: String): List<Movies>  {
        var movies: List<Movies> = moviesRemoteDataSource.getMovies(withCast, withGenres)
        return movies
    }
    fun getAllLocalMovies() = moviesLocalDataSource.getAll()
    fun saveLocal(movie: Movies) = moviesLocalDataSource.save(movie)
    fun saveAllLocal(movies: List<Movies>) = moviesLocalDataSource.saveAll(movies)
    fun deleteLocal(movie: Movies) = moviesLocalDataSource.delete(movie)
    fun deleteAllLocal() = moviesLocalDataSource.deleteAll()
    fun deleteAllLocal(movies: List<Movies>) = moviesLocalDataSource.deleteAll(movies)
    fun replaceAllLocal(movies: List<Movies>) = moviesLocalDataSource.replaceAll(movies)
    fun getCount() = moviesLocalDataSource.getCount()
}