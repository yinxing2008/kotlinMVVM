package com.cxyzy.demo.network

import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.cxyzy.demo.network.paging.RepoDataSourceFactory
import com.cxyzy.demo.network.response.RepoResp

object HttpRepository : BaseHttpRepository() {
    private val factory by lazy { RepoDataSourceFactory(api) }
    fun getRepo(): LiveData<PagedList<RepoResp>> {
        return LivePagedListBuilder(factory, config).build()
    }
}