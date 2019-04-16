package com.cxyzy.note.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.cxyzy.note.network.HttpRepository
import com.cxyzy.note.network.bean.Repo

class RepoViewModel : BaseViewModel() {
    private val tag = RepoViewModel::class.java.simpleName
    lateinit var repoList: LiveData<PagedList<Repo>>

    fun getRepoDetail(id: String, start: () -> Unit, finally: () -> Unit) {
        launchOnUITryCatch(
                {
                    start()
                    //TODO: get Repo detail
                },
                {
                    Log.e(tag, "${it.message}")
                },
                { finally() },
                true)
    }

    /**
     * @param start 这个方法中可以显示加载进度条等
     * @param finally 可以隐藏进度条等
     */
    fun getRepo(start: () -> Unit, catch: (throwable: Throwable) -> Unit, finally: () -> Unit) {
        launchOnUITryCatch(
                {
                    start()
                    repoList = HttpRepository.getRepo()
                },
                {
                    catch(it)
                    Log.e(tag, "${it.message}")
                },
                { finally() },
                true)
    }
}