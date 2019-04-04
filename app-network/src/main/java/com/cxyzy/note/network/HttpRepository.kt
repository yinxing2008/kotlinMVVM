package com.tanzhiqiang.kmvvm.repository

import com.cxyzy.note.ext.CoroutineCallAdapterFactory
import com.cxyzy.note.network.HttpLogInterceptor
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object HttpRepository {
    private fun getApiService(): Api {
        return Retrofit.Builder()
                .baseUrl("https://raw.githubusercontent.com/")
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .client(provideOkHttpClient(provideLoggingInterceptor()))
                .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
                .build()
                .create(Api::class.java)
    }

    private fun provideOkHttpClient(interceptor: HttpLoggingInterceptor): OkHttpClient = OkHttpClient.Builder().apply {
        addInterceptor(interceptor)
        addInterceptor(HttpLogInterceptor())
    }.build()

    private fun provideLoggingInterceptor(): HttpLoggingInterceptor = HttpLoggingInterceptor()
            .apply { level = HttpLoggingInterceptor.Level.BODY }

    fun getTask() = getApiService().getTask()

}