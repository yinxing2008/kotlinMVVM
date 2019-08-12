package com.cxyzy.demo.network.paging

import androidx.paging.PageKeyedDataSource
import com.cxyzy.demo.network.Api
import com.cxyzy.demo.network.response.RepoResp
import com.cxyzy.utils.LogUtils
import java.io.IOException

class RepoPageKeyedDataSource(
        private val api: Api) : PageKeyedDataSource<Int, RepoResp>(), LogUtils {

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, RepoResp>) {
    }

    private fun callAPI(page: Int, perPage: Int, callback: (repos: List<RepoResp>, next: Int?) -> Unit) {
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
            error(e)
        }
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, RepoResp>) {
        callAPI(params.key, params.requestedLoadSize) { repos, next ->
            callback.onResult(repos, next)
        }
    }

    override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Int, RepoResp>) {
        callAPI(1, params.requestedLoadSize) { repos, next ->
            callback.onResult(repos, null, next)
        }
    }
}