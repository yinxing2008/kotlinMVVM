package com.cxyzy.demo

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import com.cxyzy.demo.utils.logger.initLogger

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        context = this
        initLogger(BuildConfig.DEBUG)
    }

    companion object {
        @SuppressLint("StaticFieldLeak")
        lateinit var context: Context
    }

}