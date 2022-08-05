package com.axichise.movieapp.ui.movieDetails

import com.google.gson.annotations.SerializedName

data class MovieDetailsResponse(
    var id: Int,
    var videos: VideosList,
    @SerializedName("backdrop_path") var backdropPath: String?,
    @SerializedName("overview") var overview: String,
    @SerializedName("poster_path") var posterPath: String?,
    @SerializedName("release_date") var releaseDate: String,
    @SerializedName("title") var title: String

)