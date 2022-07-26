package com.axichise.movieapp.ui.genres


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.axichise.movieapp.R
import com.axichise.movieapp.ui.onBoardingScreen.OnBoarding
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class GenresActivity : AppCompatActivity() {

    private var genres: List<Genre> = emptyList()
    private val genresRepository = GenresRepository.instance

    private fun getGenres() {
        GlobalScope.launch(Dispatchers.IO) {
            genres = genresRepository.getAllRemoteGenres()
            withContext(Dispatchers.Main) {
                preselectSaveGenres()
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_genres)
        setOnClickListeners()
        getGenres()
    }

    private fun setOnClickListeners() {
        val btnSave = findViewById<FloatingActionButton>(R.id.check)
        btnSave.setOnClickListener {
            saveGenres()
        }
    }

    private fun getSelectedGenres(): List<Genre> {
        return genres.filter { genre -> genre.isSelected }
    }

    private fun saveGenres() {
        GlobalScope.launch(Dispatchers.IO) {
            genresRepository.deleteAllLocal()
            genresRepository.saveAllLocal(getSelectedGenres())
        }
        OnBoarding.open(this)
    }

    private fun setupRecyclerView() {
        val rvGenres = findViewById<RecyclerView>(R.id.rv_genres)
        rvGenres.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        rvGenres.adapter = GenresAdapter(genres)
    }

    private fun preselectSaveGenres() {
        GlobalScope.launch(Dispatchers.IO) {
            val savedGenres: List<Genre> = genresRepository.getAllLocalGenres()
            withContext(Dispatchers.Main) {
                genres.forEach { genre -> genre.isSelected = savedGenres.contains(genre) }
                setupRecyclerView()
            }
        }
    }
}