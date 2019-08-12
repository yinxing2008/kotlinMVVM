package com.cxyzy.demo.network

import androidx.paging.PagedList
import com.cxyzy.demo.ext.KoinInject
import com.cxyzy.demo.utils.OkHttpUrl.BASE_URL
import com.cxyzy.demo.utils.PAGE_SIZE
import com.cxyzy.demo.network.interceptor.HttpLogInterceptor
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

open class BaseHttpRepository {
    val api: Api by lazy {
        val okHttpClient = KoinInject.getFromKoin<OkHttpClient>()
        Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(okHttpClient)
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

}