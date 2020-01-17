package com.cxyzy.demo.viewmodels

import com.cxyzy.demo.db.repository.TaskRepository

object TaskViewModel : BaseViewModel() {
    val taskList = TaskRepository.getTaskList()

    fun delTask(id: Int, tryBlock: () -> Unit, catchBlock: (throwable: Throwable) -> Unit, finallyBlock: () -> Unit) {
        launchOnUITryCatch(
                {
                    tryBlock()
                    TaskRepository.delTask(id)
                },
                {
                    catchBlock(it)
                    error(it)
                },
                { finallyBlock() },
                true)
    }
}