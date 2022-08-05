package com.axichise.movieapp.ui.movieDetails

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.axichise.movieapp.network.APIClient
import com.axichise.movieapp.ui.movies.MoviesRemoteDataSource

class MovieDetailViewModel : ViewModel() {
    val currentMovieId = MutableLiveData<Int>()

    var movie: MovieDetails? = null

    private val moviesRemoteDataSource = MoviesRemoteDataSource(APIClient.instance.retrofit)

    fun getMoviesDetails(): MovieDetails? {
        return currentMovieId.value?.let { moviesRemoteDataSource.getMovieDetails(it) }
    }
}