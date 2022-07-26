package com.axichise.movieapp.ui.genres

import androidx.room.*

@Dao
interface GenreDAO {

    @Query("SELECT * from genres")
    fun getAll(): List<Genre>

    @Insert
    fun save(genre: Genre)

    @Insert
    fun saveAll(genres: List<Genre>)

    @Delete
    fun delete(genre: Genre)

    @Delete
    fun deleteAll(genre: List<Genre>)

    @Query("DELETE FROM genres")
    fun deleteAll()

    @Transaction
    fun replaceAll(genres: List<Genre>){
        deleteAll()
        saveAll(genres)
    }
}