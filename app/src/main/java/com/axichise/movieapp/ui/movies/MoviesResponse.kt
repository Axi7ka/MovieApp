package com.axichise.movieapp.ui.movies

import com.google.gson.annotations.SerializedName

class MoviesResponse (

    @SerializedName("id")
    var id: Int,

    @SerializedName("title")
    var title: String,

    @SerializedName("poster_path")
    var poster_path: String,

    @SerializedName("overview")
    var overview: String,

    @SerializedName("release_date")
    var release_date: String,

)