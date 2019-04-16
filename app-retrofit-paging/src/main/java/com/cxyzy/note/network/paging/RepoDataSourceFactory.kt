package com.cxyzy.note.network.paging

import androidx.paging.DataSource
import com.cxyzy.note.network.Api
import com.cxyzy.note.network.bean.Repo

class RepoDataSourceFactory(
        api: Api) : DataSource.Factory<Int, Repo>() {
    private val source = RepoPageKeyedDataSource(api)

    override fun create(): DataSource<Int, Repo> {
        return source
    }
}
