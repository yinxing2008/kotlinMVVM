package com.cxyzy.note.viewmodels

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.cxyzy.note.network.bean.Task
import com.cxyzy.note.network.HttpRepository
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
                    taskList.value = HttpRepository.getTask()
                }
                ,
                {
                    Log.i("tt", "${it.message}")
                }, { finally() }, true)

    }
}