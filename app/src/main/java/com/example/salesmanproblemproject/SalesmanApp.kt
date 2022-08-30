package com.example.salesmanproblemproject

import android.app.Application
import timber.log.Timber

class SalesmanApp: Application() {

    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
    }
}