package com.cxyzy.demo.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.PagedList
import com.cxyzy.demo.network.HttpRepository
import com.cxyzy.demo.network.response.RepoResp

object RepoViewModel : BaseViewModel() {
    var repoList: LiveData<PagedList<RepoResp>> = MutableLiveData()

    fun getRepoDetail(id: String, onSuccess: () -> Unit, onError: (throwable: Throwable) -> Unit, onFinish: () -> Unit) {
        launchOnUITryCatch(
                {
                    onSuccess()
                    //TODO: get RepoResp detail
                },
                {
                    onError(it)
                    error(it)
                },
                { onFinish() },
                true)
    }

    /**
     * @param onSuccess 主要执行代码块
     * @param onError 异常处理代码块
     * @param onFinish 无论是否异常都执行的代码块
     */
    fun getRepo(onSuccess: () -> Unit, onError: (throwable: Throwable) -> Unit, onFinish: () -> Unit) {
        launchOnUITryCatch(
                {
                    onSuccess()
                    repoList = HttpRepository.getRepo()
                },
                {
                    onError(it)
                    error(it)
                },
                { onFinish() },
                true)
    }
}