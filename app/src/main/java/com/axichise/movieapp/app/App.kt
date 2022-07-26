package com.axichise.movieapp.app

import com.axichise.movieapp.database.Database
import android.app.Application

class App: Application() {
    override fun onCreate() {
        super.onCreate()

        Database.instance.initialize(this)
    }
}