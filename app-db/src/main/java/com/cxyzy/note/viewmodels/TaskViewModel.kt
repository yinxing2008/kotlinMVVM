package com.cxyzy.note.viewmodels

import android.util.Log
import com.cxyzy.note.db.repository.TaskRepository
import timber.log.Timber

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
                    Timber.e(it)
                },
                { finally() },
                true)
    }
}