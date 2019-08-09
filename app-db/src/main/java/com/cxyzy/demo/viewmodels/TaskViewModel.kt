package com.cxyzy.demo.viewmodels

import com.cxyzy.demo.db.repository.TaskRepository

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
                    error(it)
                },
                { finallyBlock() },
                true)
    }
}