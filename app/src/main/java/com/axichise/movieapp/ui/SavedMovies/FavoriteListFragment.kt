package com.axichise.movieapp.ui.SavedMovies

import android.graphics.Movie
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.axichise.movieapp.R
import com.axichise.movieapp.ui.movies.Movies
import com.axichise.movieapp.ui.movies.MoviesRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class FavoriteListFragment : Fragment(R.layout.fragment_favorite_list) {

    private val moviesRep : MoviesRepository = MoviesRepository.instance
    private var movies : MutableList<Movies> = mutableListOf()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initializeListOfMovies(view)
    }
    private fun setUpRecycleView(view:View){
        val linearLayoutManager = LinearLayoutManager(view.context)
        val recyclerView = view.findViewById<RecyclerView>(R.id.rvFavoriteMoviesList)

        linearLayoutManager.orientation = LinearLayoutManager.VERTICAL
        linearLayoutManager.reverseLayout = false

        recyclerView.layoutManager = linearLayoutManager
        recyclerView.adapter = FavoriteMoviesAdapter(movies)
    }

    private fun initializeListOfMovies(view:View){
        GlobalScope.launch (Dispatchers.IO){
            movies = moviesRep.getFavorite().toMutableList()
            withContext(Dispatchers.Main){
                setUpRecycleView(view)
            }
        }
    }

}
