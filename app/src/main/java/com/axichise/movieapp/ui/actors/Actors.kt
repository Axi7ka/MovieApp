package com.axichise.movieapp.ui.actors

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import org.jetbrains.annotations.NonNls

@Entity(tableName ="actors")
data class Actors (
    @PrimaryKey
    @NonNls
    @ColumnInfo(name ="id")
    var id: Int,

    @ColumnInfo(name ="name")
    var name: String,

    @ColumnInfo(name = "profile_path")
    var profile_path: String?,

    @ColumnInfo(name ="isSelected")
    var isSelected: Boolean
        ) {
    override fun equals(other: Any?) = (other is Actors) && id == other.id

    override fun toString(): String {
        return "Actors(id=$id, name='$name',profile_patth = '$profile_path, isSelected=$isSelected)"
    }
}