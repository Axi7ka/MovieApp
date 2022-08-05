package com.axichise.movieapp.ui.actors

import androidx.room.*

@Dao
interface ActorsDAO {

    @Query("SELECT * from actors")
    fun getAll(): List<Actors>

    @Query("SELECT id from actors")
    fun getAllIds(): List<Int>

    @Insert
    fun save(actors: Actors)

    @Insert
    fun saveAll(actors: List<Actors>)

    @Delete
    fun delete(actors: Actors)

    @Delete
    fun deleteAll(actors: List<Actors>)

    @Query("DELETE FROM actors")
    fun deleteAll()

    @Transaction
    fun replaceAll(actors: List<Actors>) {
        deleteAll()
        saveAll(actors)
    }

    @Query("SELECT COUNT(id) FROM actors")
    fun getCount(): Int
}