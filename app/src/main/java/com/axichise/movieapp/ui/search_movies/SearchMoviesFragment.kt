package com.axichise.movieapp.ui.search_movies

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.axichise.movieapp.R
import com.axichise.movieapp.databinding.FragmentSearchMoviesBinding
import com.axichise.movieapp.ui.actors.ActorsRepository
import com.axichise.movieapp.ui.genres.GenresRepository
import com.axichise.movieapp.ui.movieDetails.MovieDetailViewModel
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

    private lateinit var viewModel: MovieDetailViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val searchMoviesViewModel =
            ViewModelProvider(this).get(SearchMoviesViewModel::class.java)

        _binding = FragmentSearchMoviesBinding.inflate(inflater, container, false)
        val root: View = binding.root

        viewModel = ViewModelProvider(requireActivity())[MovieDetailViewModel::class.java]

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        getQueryParams()
        getSearchedMovieQuery()
    }

    private fun getQueryParams() {
        preselectSaveGenres()
    }

    private fun preselectSaveGenres() {
        GlobalScope.launch(Dispatchers.IO) {
            val savedGenresIds: List<Int> = genresRepository.getAllLocalIds()
            genresIds = savedGenresIds.joinToString(separator = "|") { "$it" }

            val savedActorsIds: List<Int> = actorsRepository.getAllLocalIds()
            actorsIds = savedActorsIds.joinToString(separator = "|") { "$it" }

//            Log.d("Test", "Rezultat: $genresIds")
//            Log.d("Test", "Rezultat: $actorsIds")
            withContext(Dispatchers.Main) {
                getMovies()
            }
        }
    }

    private fun getMovies() {
        GlobalScope.launch(Dispatchers.IO) {
            movies = moviesRepository.getAllRemoteMovies(actorsIds, genresIds)
            withContext(Dispatchers.Main) {
                preselectItems()
                setupRecyclerView()
            }
        }
    }

    private fun setupRecyclerView() {
        val rvMovies = binding.rvMovies
        rvMovies?.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        rvMovies?.adapter = MoviesAdapter(movies, { navigateToMovieDetails() }, viewModel)

    }

    private fun getSearchedMovieQuery() {
        val search = binding.rvSearch
        search?.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(newText: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if ((newText?.length ?: 0) >= 1) {
                    getSearchedMovies(newText ?: "")
                } else
                    getMovies()
                return false
            }
        })
    }

    private fun getSearchedMovies(query: String) {
        GlobalScope.launch(Dispatchers.IO) {
            movies = moviesRepository.getAllSearchedMovies(query)
            withContext(Dispatchers.Main) {
                preselectItems()
                binding.rvMovies.adapter =
                    MoviesAdapter(movies, { navigateToMovieDetails() }, viewModel)
            }
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private fun preselectItems() {
        GlobalScope.launch(Dispatchers.IO) {
            val saved = moviesRepository.getAllLocalMovies()
            withContext(Dispatchers.Main) {
                movies.forEach {
                    val idx = saved.indexOf(it)
                    it.isFavorite = (idx != -1) && saved[idx].isFavorite
                    it.isWatched = (idx != -1) && saved[idx].isWatched
                }
            }
        }
    }

    private fun navigateToMovieDetails() {
        findNavController().navigate(R.id.fragmentMovieDetails)

    }

}