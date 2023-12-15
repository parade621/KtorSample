package com.example.ktorsample

import android.app.Application
import timber.log.Timber

class KtorSample: Application() {

    override fun onCreate() {
        super.onCreate()

        Timber.plant(Timber.DebugTree())
    }
}