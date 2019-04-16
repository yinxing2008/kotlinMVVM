package com.cxyzy.note.viewmodels

import com.cxyzy.note.db.repository.TaskRepository
import timber.log.Timber

class TaskViewModel : BaseViewModel() {
    private val taskRepository = TaskRepository.getInstance()
    val taskList = taskRepository.getTaskList()

    fun delTask(id: Int, tryBlock: () -> Unit, catchBlock: (throwable: Throwable) -> Unit, finallyBlock: () -> Unit) {
        launchOnUITryCatch(
                {
                    tryBlock()
                    taskRepository.delTask(id)
                },
                {
                    catchBlock(it)
                    Timber.e(it)
                },
                { finallyBlock() },
                true)
    }
}