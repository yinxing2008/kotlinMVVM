package com.cxyzy.note.network

import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.cxyzy.note.ext.CoroutineCallAdapterFactory
import com.cxyzy.note.network.bean.Repo
import com.cxyzy.note.network.paging.RepoDataSourceFactory
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object HttpRepository {
    private val factory by lazy { RepoDataSourceFactory(api) }
    fun getRepo(): LiveData<PagedList<Repo>> {
        return LivePagedListBuilder(factory, config).build()
    }

    private const val PAGE_SIZE = 10

    private val api: Api by lazy {
        Retrofit.Builder()
                .baseUrl("https://api.github.com")
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .client(provideOkHttpClient(provideLoggingInterceptor()))
                .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
                .build()
                .create(Api::class.java)
    }

    private val config by lazy {
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