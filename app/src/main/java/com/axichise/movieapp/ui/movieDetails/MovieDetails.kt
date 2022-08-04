package com.axichise.movieapp.ui.movieDetails

import com.axichise.movieapp.ui.genres.Genre

data class MovieDetails (
    var id: Int?,
    var backdropPath: String?,
    var overview: String,
    var posterPath: String?,
    var releaseDate: String?,
    var title: String,
    var videos: VideosList
)