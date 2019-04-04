package com.cxyzy.note.viewmodels

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.paging.PagedList
import com.cxyzy.note.network.bean.Task
import com.tanzhiqiang.kmvvm.repository.HttpRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class TaskViewModel : BaseViewModel() {

    val taskList: MutableLiveData<List<Task>> = MutableLiveData()

    fun delTask(id: Int, start: () -> Unit, finally: () -> Unit) {
        launchOnUITryCatch(
                {
                    start()
//                    taskRepository.delTask(id)
                },
                {
                    Log.i("tt", "${it.message}")
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
                    val tasks = withContext(Dispatchers.IO) { HttpRepository.getTask() }
                    taskList.value = tasks.await()
                }
                ,
                {
                    Log.i("tt", "${it.message}")
                }, { finally() }, true)

    }
}