package com.cxyzy.demo.network.paging

import androidx.paging.DataSource
import com.cxyzy.demo.network.Api
import com.cxyzy.demo.network.response.Repo

class RepoDataSourceFactory(
        api: Api) : DataSource.Factory<Int, Repo>() {
    private val source = RepoPageKeyedDataSource(api)

    override fun create(): DataSource<Int, Repo> {
        return source
    }
}
