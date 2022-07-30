package com.axichise.movieapp.ui.actors

import com.axichise.movieapp.database.Database
import com.axichise.movieapp.network.APIClient

class ActorsRepository private constructor() {
    companion object {
        val instance = ActorsRepository()
    }

    private val actorsRemoteDataSource = ActorsRemoteDataSource(APIClient.instance.retrofit)
    private val actorsLocalDataSource = ActorsLocalDataSource(Database.instance)

    fun getAllRemoteActors() = actorsRemoteDataSource.getActors()
    fun getAllLocalActors() = actorsLocalDataSource.getAll()
    fun saveLocal(actors: Actors) = actorsLocalDataSource.save(actors)
    fun saveAllLocal(actors: List<Actors>) = actorsLocalDataSource.saveAll(actors)
    fun deleteLocal(actors: Actors) = actorsLocalDataSource.delete(actors)
    fun deleteAllLocal() = actorsLocalDataSource.deleteAll()
    fun deleteAllLocal(actors: List<Actors>) = actorsLocalDataSource.deleteAll(actors)
    fun replaceAllLocal(actors: List<Actors>) = actorsLocalDataSource.replaceAll(actors)
    fun getCount() = actorsLocalDataSource.getCount()
}

