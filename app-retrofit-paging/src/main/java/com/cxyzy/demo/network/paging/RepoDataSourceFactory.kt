package com.cxyzy.demo.network.paging

import androidx.paging.DataSource
import com.cxyzy.demo.network.Api
import com.cxyzy.demo.network.response.RepoResp

class RepoDataSourceFactory(
        api: Api) : DataSource.Factory<Int, RepoResp>() {
    private val source = RepoPageKeyedDataSource(api)

    override fun create(): DataSource<Int, RepoResp> {
        return source
    }
}
