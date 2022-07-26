package com.axichise.movieapp.ui.onBoardingScreen

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.axichise.movieapp.R
import com.axichise.movieapp.ui.actors.ActorsActivity
import com.axichise.movieapp.ui.genres.GenresActivity

class OnBoarding : AppCompatActivity() {

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

    private fun setClickListener(){
        val genresButton = findViewById<Button>(R.id.btnGenres)
        genresButton.setOnClickListener {
            startActivity(Intent(this,GenresActivity::class.java))
        }

        val actorsButton = findViewById<Button>(R.id.btnActors)
        actorsButton.setOnClickListener {
            startActivity(Intent(this,ActorsActivity::class.java))
        }
    }

}