package com.cxyzy.note.viewmodels

import android.util.Log
import com.cxyzy.note.network.HttpRepository
import com.cxyzy.note.network.bean.Repo

class TaskViewModel : BaseViewModel() {
    private val tag = TaskViewModel::class.java.simpleName
    lateinit var taskList: List<Repo>

    fun delTask(id: Int, start: () -> Unit, finally: () -> Unit) {
        launchOnUITryCatch(
                {
                    start()
//                    taskRepository.delTask(id)
                },
                {
                    Log.i(tag, "${it.message}")
                },
                { finally() },
                true)
    }

    /**
     * @param start 这个方法中可以显示加载进度条等
     * @param finally 可以隐藏进度条等
     */
    fun getTaskFromNetwork(start: () -> Unit, finally: () -> Unit) {
        launchOnUITryCatch(
                {
                    start()
                    taskList = HttpRepository.getTask().await()
                }
                ,
                {
                    Log.i(tag, "${it.message}")
                }, { finally() }, true)

    }
}