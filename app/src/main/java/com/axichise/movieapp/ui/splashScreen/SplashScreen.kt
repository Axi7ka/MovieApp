package com.axichise.movieapp.ui.splashScreen

import androidx.appcompat.app.AppCompatActivity
import com.axichise.movieapp.R
import android.annotation.SuppressLint
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.axichise.movieapp.SearchScreenActivity
import com.axichise.movieapp.ui.genres.GenresRepository
import com.axichise.movieapp.ui.onBoardingScreen.OnBoarding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

private const val DELAY = 500L

@SuppressLint("CustomSplashScreen")
class SplashActivity : AppCompatActivity() {

    private var handler: Handler? = null
    private var runnable: Runnable? = null
    private val genresRepository = GenresRepository.instance

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
        initHandlerToOpenNextActivity()
    }

    private fun initHandlerToOpenNextActivity() {
        handler = Handler(Looper.getMainLooper())
        runnable = Runnable {
            openNextScreen()
        }

        handler?.postDelayed(runnable!!, DELAY)
    }

    private fun openNextScreen() {
        isSaved()

        finish()
    }

    private fun isSaved() {
        GlobalScope.launch(Dispatchers.IO) {
            val genreCount = genresRepository.getCount()
            withContext(Dispatchers.Main) {
                verifyIsSaved(genreCount)
            }
        }
    }
        private fun verifyIsSaved(genreCount: Int){
            val isSaved = genreCount >0
            if(isSaved)
                SearchScreenActivity.open(this)
            else
                OnBoarding.open(this)

    }

    override fun onDestroy() {
        removeHandler()
        super.onDestroy()
    }

    override fun onBackPressed() {
        removeHandler()
        super.onBackPressed()
    }

    private fun removeHandler() {
        if (handler != null && runnable != null) {
            handler?.removeCallbacks(runnable!!)
            runnable = null
            handler = null
        }
    }
}