package com.cxyzy.demo.viewmodels

import com.cxyzy.demo.db.repository.TaskRepository
import com.cxyzy.demo.ext.KoinInject

class TaskViewModel : BaseViewModel() {
    private val taskRepository = KoinInject.getFromKoin<TaskRepository>()
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