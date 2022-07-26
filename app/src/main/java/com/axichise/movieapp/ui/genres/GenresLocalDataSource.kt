package com.axichise.movieapp.ui.genres

import com.axichise.movieapp.database.Database

class GenresLocalDataSource(database: Database) {
    private val genreDAO: GenreDAO = database.movieAppDatabase.genresDao()

    fun getAll() = genreDAO.getAll()
    fun getAllIds() = genreDAO.getAllIds()
    fun save(genre: Genre) = genreDAO.save(genre)
    fun saveAll(genres: List<Genre>) = genreDAO.saveAll(genres)
    fun delete(genre: Genre) = genreDAO.delete(genre)
    fun deleteAll() = genreDAO.deleteAll()
    fun deleteAll(genres: List<Genre>) = genreDAO.deleteAll(genres)
    fun replaceAll(genres: List<Genre>) = genreDAO.replaceAll(genres)
    fun getCount() = genreDAO.getCount()
}