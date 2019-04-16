package com.cxyzy.note.network

import android.nfc.tech.MifareUltralight.PAGE_SIZE
import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.cxyzy.note.ext.CoroutineCallAdapterFactory
import com.cxyzy.note.network.bean.Task
import com.cxyzy.note.network.paging.TaskDataSourceFactory
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object HttpRepository {
    private fun getApiService(): Api {
        return Retrofit.Builder()
                .baseUrl("https://raw.githubusercontent.com/")
//                .addCallAdapterFactory(CoroutineCallAdapterFactory())
//                .client(provideOkHttpClient(provideLoggingInterceptor()))
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


    fun getTask(): LiveData<PagedList<Task>> {

        val api = getApiService()
        val factory = TaskDataSourceFactory(api)
        val config = PagedList.Config.Builder()
                .setInitialLoadSizeHint(PAGE_SIZE)
                .setPageSize(PAGE_SIZE)
                .build()

        return LivePagedListBuilder(factory, config).build()

    }

}