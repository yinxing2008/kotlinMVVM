package com.cxyzy.demo.ext

import com.cxyzy.demo.App
import com.cxyzy.demo.R
import com.cxyzy.demo.utils.HttpsUtil
import com.cxyzy.note.network.interceptor.HttpLogInterceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor

fun provideOkHttpClient(): OkHttpClient {
    val sslParams = HttpsUtil.getSslSocketFactory(arrayOf(App.context.resources.openRawResource(R.raw.https_keystore)), null, null)
    return OkHttpClient.Builder()
            .sslSocketFactory(sslParams.sSLSocketFactory, sslParams.trustManager)
            .apply {
                addInterceptor(HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY })
                addInterceptor(HttpLogInterceptor())
            }.build()
}