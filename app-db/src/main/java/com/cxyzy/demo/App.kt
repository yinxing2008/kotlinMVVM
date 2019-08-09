package com.cxyzy.demo

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        context = this
        startKoinModules()
    }
    private fun startKoinModules() {
        startKoin {
            androidContext(this@App)
            modules(appModule)
        }
    }
    companion object {
        @SuppressLint("StaticFieldLeak")
        lateinit var context: Context
    }

}