package com.axichise.movieapp.ui.actors

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.axichise.movieapp.ui.genres.Genre
import org.jetbrains.annotations.NonNls

@Entity(tableName ="actors")
data class Actors (
    @PrimaryKey
    @NonNls
    @ColumnInfo(name ="id")
    var id: Int,

    @ColumnInfo(name ="name")
    var name: String,

    @ColumnInfo(name ="isSelected")
    var isSelected: Boolean
        ) {
    override fun equals(other: Any?) = (other is Genre) && id == other.id

    override fun toString(): String {
        return "Genre(id=$id, name='$name', isSelected=$isSelected)"
    }
}