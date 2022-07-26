package com.axichise.movieapp.ui.movies

import com.axichise.movieapp.database.Database

class MoviesLocalDataSource(database: Database) {
    private val moviesDAO: MoviesDAO = database.movieAppDatabase.moviesDao()

    fun getAll() = moviesDAO.getAll()
    fun save(movies: Movies) = moviesDAO.save(movies)
    fun saveAll(movies: List<Movies>) = moviesDAO.saveAll(movies)
    fun delete(movies: Movies) = moviesDAO.delete(movies)
    fun deleteAll() = moviesDAO.deleteAll()
    fun deleteAll(movies: List<Movies>) = moviesDAO.deleteAll(movies)
    fun replaceAll(movies: List<Movies>) = moviesDAO.replaceAll(movies)
    fun getCount() = moviesDAO.getCount()
}