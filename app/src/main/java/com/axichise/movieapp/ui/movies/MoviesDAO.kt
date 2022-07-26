package com.axichise.movieapp.ui.movies

import androidx.room.*

@Dao
interface MoviesDAO {

    @Query("SELECT * from movies")
    fun getAll(): List<Movies>

    @Insert
    fun save(movies: Movies)

    @Insert
    fun saveAll(movies: List<Movies>)

    @Delete
    fun delete(movies: Movies)

    @Delete
    fun deleteAll(movies: List<Movies>)

    @Query("DELETE FROM movies")
    fun deleteAll()

    @Transaction
    fun replaceAll(movies: List<Movies>){
        deleteAll()
        saveAll(movies)
    }

    @Query("SELECT COUNT(id) FROM genres")
    fun getCount() :Int
}