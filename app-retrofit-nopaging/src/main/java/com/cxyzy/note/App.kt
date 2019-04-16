package com.cxyzy.note

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import timber.log.Timber
import timber.log.Timber.DebugTree



class App : Application() {

    override fun onCreate() {
        super.onCreate()
        context = this

        initLog()
    }

    private fun initLog() {
        if (BuildConfig.DEBUG) {
            Timber.plant(DebugTree())
        }
    }

    companion object {
        @SuppressLint("StaticFieldLeak")
        lateinit var context: Context
    }

}