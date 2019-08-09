package com.cxyzy.demo.network

import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.cxyzy.demo.network.response.Repo
import com.cxyzy.demo.network.paging.RepoDataSourceFactory

object HttpRepository : BaseHttpRepository() {
    private val factory by lazy { RepoDataSourceFactory(api) }
    fun getRepo(): LiveData<PagedList<Repo>> {
        return LivePagedListBuilder(factory, config).build()
    }
}