package com.axichise.movieapp.ui.actors

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.axichise.movieapp.R
import com.axichise.movieapp.ui.genres.Genre
import com.axichise.movieapp.ui.onBoardingScreen.OnBoarding
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ActorsActivity : AppCompatActivity() {

    private var actors:List<Actors> = emptyList()
    private val actorsRepository = ActorsRepository.instance

    private fun getActors(){
        GlobalScope.launch(Dispatchers.IO){
            actors = actorsRepository.getAllRemoteActors()
            withContext(Dispatchers.Main){
                setupRecyclerView()
            }
        }
        setupRecyclerView()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_actors)
        getActors()
    }

    private fun setOnClickListeners() {
        val btnSave = findViewById<FloatingActionButton>(R.id.check)
        btnSave.setOnClickListener {
            saveActors()
        }
    }

    private fun getSelectedActors(): List<Actors> {
        return actors.filter { actors -> actors.isSelected }
    }

    private fun saveActors() {
        GlobalScope.launch(Dispatchers.IO) {
            actorsRepository.deleteAllLocal()
            actorsRepository.saveAllLocal(getSelectedActors())
        }
        OnBoarding.open(this)
    }

    private fun setupRecyclerView() {
        val rvActors = findViewById<RecyclerView>(R.id.rv_actors)
        rvActors.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        rvActors.adapter = ActorsAdapter(actors)
    }

    private fun preselectSavedActors() {
        GlobalScope.launch(Dispatchers.IO) {
            val savedActors: List<Actors> = actorsRepository.getAllLocalActors()
            withContext(Dispatchers.Main) {
                actors.forEach { actor -> actor.isSelected = savedActors.contains(actor) }
                setupRecyclerView()
            }
        }
    }
}