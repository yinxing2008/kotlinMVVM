
package com.cxyzy.note.network.paging

import androidx.paging.DataSource
import com.cxyzy.note.network.Api
import com.cxyzy.note.network.bean.Repo

/**
 * A simple data source factory which also provides a way to observe the last created data source.
 * This allows us to channel its network request status etc back to the UI. See the Listing creation
 * in the Repository class.
 */
class DataSourceFactory(
        api: Api) : DataSource.Factory<Int, Repo>() {
    private val source = PageKeyedRepoDataSource(api)

    override fun create(): DataSource<Int, Repo> {
        return source
    }
}
