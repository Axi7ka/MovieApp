package com.axichise.movieapp.ui.movies

class MoviesMapper {
    fun map(moviesResponse: MoviesResponse): Movies {
        return Movies(
            id = moviesResponse.id,
            title = moviesResponse.title,
            poster_path = moviesResponse.poster_path,
            overview = moviesResponse.overview,
            release_date = moviesResponse.release_date,
            isSelected = false
        )
    }
}