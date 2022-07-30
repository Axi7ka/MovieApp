package com.axichise.movieapp.ui.search_movies

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.axichise.movieapp.R
import com.axichise.movieapp.ui.movies.Movies
import com.axichise.movieapp.ui.movies.MoviesAdapter
import com.axichise.movieapp.ui.movies.MoviesRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SearchMoviesFragment : Fragment(R.layout.fragment_search_movies) {

    //private var _binding: FragmentSearchMoviesBinding? = null
    private var movies: List<Movies> = emptyList()
    private val moviesRepository = MoviesRepository.instance

    //private val binding get() = _binding!!

    private fun getMovies(view: View){
        GlobalScope.launch(Dispatchers.IO){
            movies = moviesRepository.getAllRemoteMovies()
            withContext(Dispatchers.Main){
                setupRecyclerView()
            }
        }
    }

    private fun setupRecyclerView() {
        val rvMovies = view?.findViewById<RecyclerView>(R.id.rv_movies)
        rvMovies?.layoutManager =
            LinearLayoutManager(view?.context, LinearLayoutManager.VERTICAL, false)
        rvMovies?.adapter = MoviesAdapter(movies)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getMovies(view)
    }

}