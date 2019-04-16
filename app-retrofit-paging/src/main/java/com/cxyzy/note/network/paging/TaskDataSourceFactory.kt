
package com.cxyzy.note.network.paging

import androidx.paging.DataSource
import com.cxyzy.note.network.Api
import com.cxyzy.note.network.bean.Task

/**
 * A simple data source factory which also provides a way to observe the last created data source.
 * This allows us to channel its network request status etc back to the UI. See the Listing creation
 * in the Repository class.
 */
class TaskDataSourceFactory(
        api: Api) : DataSource.Factory<Int, Task>() {
    private val source = PageKeyedTaskDataSource(api)

    override fun create(): DataSource<Int, Task> {
        return source
    }
}
