package com.cxyzy.note.network.paging

import android.util.Log
import androidx.paging.PageKeyedDataSource
import com.cxyzy.note.network.Api
import com.cxyzy.note.network.bean.Repo
import java.io.IOException

class PageKeyedRepoDataSource(
        private val api: Api) : PageKeyedDataSource<Int, Repo>() {
    private val tag = PageKeyedRepoDataSource::class.java.simpleName

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Repo>) {
    }

    private fun callAPI(page: Int, perPage: Int, callback: (repos: List<Repo>, next: Int?) -> Unit) {
        try {
            val response = api.repos("cxyzy1", page, perPage).execute()

            response.body()?.let {
                var next: Int? = null
                response.headers().get("Link")?.let {
                    val regex = Regex("rel=\"next\"")
                    if (regex.containsMatchIn(it)) {
                        next = page + 1
                    }
                }

                callback(it, next)
            }
        } catch (e: IOException) {
            Log.e(tag, e.message)
        }
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Repo>) {
        callAPI(params.key, params.requestedLoadSize) { repos, next ->
            callback.onResult(repos, next)
        }
    }

    override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Int, Repo>) {
        callAPI(1, params.requestedLoadSize) { repos, next ->
            callback.onResult(repos, null, next)
        }
    }
}