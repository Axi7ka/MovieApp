package com.axichise.movieapp.ui.search_movies

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.axichise.movieapp.R
import com.axichise.movieapp.databinding.FragmentSearchMoviesBinding
import com.axichise.movieapp.ui.actors.ActorsRepository
import com.axichise.movieapp.ui.genres.Genre
import com.axichise.movieapp.ui.genres.GenresRepository
import com.axichise.movieapp.ui.movies.Movies
import com.axichise.movieapp.ui.movies.MoviesAdapter
import com.axichise.movieapp.ui.movies.MoviesRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SearchMoviesFragment : Fragment(R.layout.fragment_search_movies) {

    private var _binding: FragmentSearchMoviesBinding? = null
    private var movies: List<Movies> = emptyList()
    private val moviesRepository = MoviesRepository.instance
    private val genresRepository = GenresRepository.instance
    private val actorsRepository = ActorsRepository.instance

    private var genresIds = ""
    private var actorsIds = ""
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val searchMoviesViewModel =
            ViewModelProvider(this).get(SearchMoviesViewModel::class.java)

        _binding = FragmentSearchMoviesBinding.inflate(inflater,container,false)
        val root: View = binding.root

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        getQueryParams()
        getSearchedMovieQuery()
    }

    private fun getQueryParams(){
        preselectSaveGenres()
    }

    private fun preselectSaveGenres() {
        GlobalScope.launch(Dispatchers.IO) {
            val savedGenresIds: List<Int> = genresRepository.getAllLocalIds()
            genresIds = savedGenresIds.joinToString(separator = "|"){"$it"}

            val savedActorsIds: List<Int> = actorsRepository.getAllLocalIds()
            actorsIds = savedActorsIds.joinToString(separator = "|"){"$it"}

//            Log.d("Test", "Rezultat: $genresIds")
//            Log.d("Test", "Rezultat: $actorsIds")
            withContext(Dispatchers.Main) {
                getMovies()
            }
        }
    }

    private fun getMovies(){
        GlobalScope.launch(Dispatchers.IO){
            movies = moviesRepository.getAllRemoteMovies(actorsIds,genresIds)
            withContext(Dispatchers.Main){
                setupRecyclerView()
            }
        }
    }

    private fun setupRecyclerView() {
        val rvMovies = binding.rvMovies
        rvMovies?.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        rvMovies?.adapter = MoviesAdapter(movies)

    }

    private fun getSearchedMovieQuery(){
        val search = binding.rvSearch
        search?.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(newText: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if((newText?.length ?:0) >= 1) {
                    getSearchedMovies(newText?:"")
                }
                else
                    getMovies()
                return false
            }
        })
    }
    private fun getSearchedMovies(query : String){
        GlobalScope.launch (Dispatchers.IO) {
            movies = moviesRepository.getAllSearchedMovies(query)
            withContext(Dispatchers.Main){
                binding.rvMovies.adapter=MoviesAdapter(movies)
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}