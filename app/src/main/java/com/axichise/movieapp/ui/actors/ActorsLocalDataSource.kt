package com.axichise.movieapp.ui.actors

import com.axichise.movieapp.database.Database

class ActorsLocalDataSource(database: Database) {
    private val actorsDAO: ActorsDAO = database.movieAppDatabase.actorsDao()

    fun getAll() = actorsDAO.getAll()
    fun getAllIds() = actorsDAO.getAllIds()
    fun save(actors: Actors) = actorsDAO.save(actors)
    fun saveAll(actors: List<Actors>) = actorsDAO.saveAll(actors)
    fun delete(actors: Actors) = actorsDAO.delete(actors)
    fun deleteAll() = actorsDAO.deleteAll()
    fun deleteAll(actors: List<Actors>) = actorsDAO.deleteAll(actors)
    fun replaceAll(actors: List<Actors>) = actorsDAO.replaceAll(actors)
    fun getCount() = actorsDAO.getCount()
}