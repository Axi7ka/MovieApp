package com.axichise.movieapp.ui.onBoardingScreen

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.axichise.movieapp.R
import com.axichise.movieapp.SearchScreenActivity
import com.axichise.movieapp.ui.actors.ActorsActivity
import com.axichise.movieapp.ui.actors.ActorsRepository
import com.axichise.movieapp.ui.genres.GenresActivity
import com.axichise.movieapp.ui.genres.GenresRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class OnBoarding : AppCompatActivity() {

    private val genresRepository = GenresRepository.instance
    private val actorsRepository = ActorsRepository.instance

    companion object {
        fun open(context: Context) {
            context.startActivity(Intent(context, OnBoarding::class.java))
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_on_boarding_screen)

        setClickListener()
    }

    private fun setClickListener() {
        val genresButton = findViewById<Button>(R.id.btnGenres)
        genresButton.setOnClickListener {
            startActivity(Intent(this, GenresActivity::class.java))
        }

        val actorsButton = findViewById<Button>(R.id.btnActors)
        actorsButton.setOnClickListener {
            startActivity(Intent(this, ActorsActivity::class.java))
        }
    }

    override fun onResume() {
        super.onResume()
        verifyFilterIsSelected()
    }

    private fun verifyFilterIsSelected() {
        GlobalScope.launch(Dispatchers.IO) {
            val genreCount = genresRepository.getCount()
            val actorCount = actorsRepository.getCount()
            withContext(Dispatchers.Main) {
                if (genreCount > 0 && actorCount > 0) {
                    SearchScreenActivity.open(this@OnBoarding)
                }
            }
        }
    }

}