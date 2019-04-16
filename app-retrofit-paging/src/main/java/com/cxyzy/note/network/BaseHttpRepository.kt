package com.cxyzy.note.network

import androidx.paging.PagedList
import com.cxyzy.note.network.interceptor.HttpLogInterceptor
import com.cxyzy.note.utils.PAGE_SIZE
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

open class BaseHttpRepository {
    val api: Api by lazy {
        Retrofit.Builder()
                .baseUrl("https://api.github.com")
                .client(provideOkHttpClient(provideLoggingInterceptor()))
                .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
                .build()
                .create(Api::class.java)
    }

    val config by lazy {
        PagedList.Config.Builder()
                .setInitialLoadSizeHint(PAGE_SIZE)
                .setPageSize(PAGE_SIZE)
                .build()
    }

    private fun provideOkHttpClient(interceptor: HttpLoggingInterceptor): OkHttpClient = OkHttpClient.Builder().apply {
        addInterceptor(interceptor)
        addInterceptor(HttpLogInterceptor())
    }.build()

    private fun provideLoggingInterceptor(): HttpLoggingInterceptor = HttpLoggingInterceptor()
            .apply { level = HttpLoggingInterceptor.Level.BODY }

}