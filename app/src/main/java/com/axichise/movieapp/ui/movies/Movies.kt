package com.axichise.movieapp.ui.movies

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import org.jetbrains.annotations.NonNls

    @Entity(tableName = "movies")
    data class Movies(
        @PrimaryKey
        @NonNls

        @ColumnInfo(name = "id")
        var id: Int,
        @ColumnInfo(name = "title")
        var title: String,
        @ColumnInfo(name = "poster")
        var poster_path: String,
        @ColumnInfo(name = "overview")
        var overview: String,
        @ColumnInfo(name = "release_date")
        var release_date: String,
        @ColumnInfo(name ="isSelected")
        var isSelected:Boolean

    ) {
        override fun equals(other: Any?) = (other is Movies) && id == other.id

        override fun toString(): String {
            return "Movies(id=$id, title='$title', poster=$poster_path, overview=$overview, release_date=$release_date)"

        }
    }

