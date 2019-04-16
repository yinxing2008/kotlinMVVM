package com.cxyzy.note.viewmodels

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.cxyzy.note.network.HttpRepository
import com.cxyzy.note.network.bean.Repo

class RepoViewModel : BaseViewModel() {
    private val tag = RepoViewModel::class.java.simpleName
    var taskList: MutableLiveData<List<Repo>> = MutableLiveData()

    fun getRepoDetail(id: String, start: () -> Unit, finally: () -> Unit) {
        launchOnUITryCatch(
                {
                    start()
//                    taskRepository.getRepoDetail(id)
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
    fun getRepo(start: () -> Unit, finally: () -> Unit) {
        launchOnUITryCatch(
                {
                    start()
                    taskList.value = HttpRepository.getTask()
                },
                {
                    Log.e(tag, "${it.message}")
                },
                { finally() },
                true)

    }
}