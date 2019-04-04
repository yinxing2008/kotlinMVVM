package com.cxyzy.note.viewmodels

import android.util.Log
import com.cxyzy.note.db.repository.TaskRepository

class TaskViewModel : BaseViewModel() {

    private val taskRepository = TaskRepository.getInstance()
    val taskList = taskRepository.getTaskList()

    fun delTask(id: Int, start: () -> Unit, finally: () -> Unit) {
        launchOnUITryCatch(
                {
                    start()
                    taskRepository.delTask(id)
                },
                {
                    Log.i("tt", "${it.message}")
                },
                { finally() },
                true)
    }
}