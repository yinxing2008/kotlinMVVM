package com.cxyzy.note.network

import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.cxyzy.note.network.bean.Repo
import com.cxyzy.note.network.paging.RepoDataSourceFactory

object HttpRepository : BaseHttpRepository() {
    private val factory by lazy { RepoDataSourceFactory(api) }
    fun getRepo(): LiveData<PagedList<Repo>> {
        return LivePagedListBuilder(factory, config).build()
    }
}