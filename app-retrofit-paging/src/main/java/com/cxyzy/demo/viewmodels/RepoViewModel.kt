package com.cxyzy.demo.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.PagedList
import com.cxyzy.demo.network.HttpRepository
import com.cxyzy.demo.network.response.RepoResp

object RepoViewModel : BaseViewModel() {
//    lateinit var repoList: LiveData<PagedList<RepoResp>>

    var repoList: LiveData<PagedList<RepoResp>> = MutableLiveData()

    fun getRepoDetail(id: String, tryBlock: () -> Unit, catchBlock: (throwable: Throwable) -> Unit, finallyBlock: () -> Unit) {
        launchOnUITryCatch(
                {
                    tryBlock()
                    //TODO: get RepoResp detail
                },
                {
                    catchBlock(it)
                    error(it)
                },
                { finallyBlock() },
                true)
    }

    /**
     * @param tryBlock 主要执行代码块
     * @param catchBlock 异常处理代码块
     * @param finallyBlock 无论是否异常都执行的代码块
     */
    fun getRepo(tryBlock: () -> Unit, catchBlock: (throwable: Throwable) -> Unit, finallyBlock: () -> Unit) {
        launchOnUITryCatch(
                {
                    tryBlock()
                    repoList = HttpRepository.getRepo()
                },
                {
                    catchBlock(it)
                    error(it)
                },
                { finallyBlock() },
                true)

    }
}