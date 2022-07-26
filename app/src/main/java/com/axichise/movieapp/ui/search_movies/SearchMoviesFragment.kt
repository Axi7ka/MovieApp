package com.axichise.movieapp.ui.search_movies

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.axichise.movieapp.R
import com.axichise.movieapp.databinding.FragmentSearchMoviesBinding
import com.axichise.movieapp.ui.actors.ActorsAdapter
import com.axichise.movieapp.ui.genres.Genre
import com.axichise.movieapp.ui.genres.GenresAdapter
import com.axichise.movieapp.ui.genres.GenresRepository
import com.axichise.movieapp.ui.movies.Movies
import com.axichise.movieapp.ui.movies.MoviesAdapter
import com.axichise.movieapp.ui.movies.MoviesRepository
import com.axichise.movieapp.ui.onBoardingScreen.OnBoarding
import com.google.android.material.floatingactionbutton.FloatingActionButton
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
                val rvMovies = view?.findViewById<RecyclerView>(R.id.rv_movies)
                rvMovies?.layoutManager =
                    LinearLayoutManager(view.context, LinearLayoutManager.VERTICAL, false)
                rvMovies?.adapter = MoviesAdapter(movies)

            }
        }
    }


//    override fun onCreateView(
//        inflater: LayoutInflater,
//        container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View {
//        val homeViewModel =
//            ViewModelProvider(this).get(SearchMoviesViewModel::class.java)
//
//        _binding = FragmentSearchMoviesBinding.inflate(inflater, container, false)
//        val root: View = binding.root
//
//        val textView: TextView = binding.textHome
//        homeViewModel.text.observe(viewLifecycleOwner) {
//            textView.text = it
//        }
//        return root
//    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getMovies(view)
    }

//    override fun onDestroyView() {
//        super.onDestroyView()
//        _binding = null
//    }

//    private fun preselectSavedGenres(){
//        GlobalScope.launch(Dispatchers.IO){
//            val savedGenresIds : List<Int> = genreRepository.gelAllLocalIds()
//            val preparedString = savedGenresIds.joinToString(separator = "|") {"$it"}
//
//            Log.d("Test", "Rezultat" $genreIds)
//            withContext(Dispatchers.Main){
//                getMovies(view)
//            }
//        }
//        }
}


