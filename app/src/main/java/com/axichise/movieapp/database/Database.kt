package com.axichise.movieapp.database

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import com.axichise.movieapp.MovieDetailsScreen
import com.axichise.movieapp.ui.actors.Actors
import com.axichise.movieapp.ui.actors.ActorsDAO
import com.axichise.movieapp.ui.genres.Genre
import com.axichise.movieapp.ui.genres.GenreDAO

class Database private constructor(){
    companion object{
        val instance = Database()
    }

    @androidx.room.Database(
        entities = [Genre::class, Actors::class],
        version = 2
    )

    abstract class MovieAppDatabase: RoomDatabase(){
        abstract fun genresDao(): GenreDAO
        abstract fun actorsDao(): ActorsDAO
    }

    lateinit var movieAppDatabase: MovieAppDatabase
        private set

    fun initialize(context: Context){
        this.movieAppDatabase = Room.databaseBuilder(
            context,
            MovieAppDatabase:: class.java,
            "movie_app.db"
        ).fallbackToDestructiveMigration().build()

    }
}