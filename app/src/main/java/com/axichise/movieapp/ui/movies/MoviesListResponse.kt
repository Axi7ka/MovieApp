package com.axichise.movieapp.ui.movies

import com.google.gson.annotations.SerializedName

class MoviesListResponse(
    @SerializedName("results")
    val movies: List<MoviesResponse>
)
