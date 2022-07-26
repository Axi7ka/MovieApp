package com.axichise.movieapp.ui.actors

import androidx.room.*

@Dao
interface ActorsDAO {

    @Query("SELECT * from actors")
    fun getAll(): List<Actors>

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
    fun replaceAll(actors: List<Actors>){
        deleteAll()
        saveAll(actors)
    }

    @Query("SELECT COUNT(id) FROM genres")
    fun getCount() :Int
}