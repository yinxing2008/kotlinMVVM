package com.cxyzy.demo.viewmodels

import com.cxyzy.demo.db.repository.TaskRepository

object TaskViewModel : BaseViewModel() {
    val taskList = TaskRepository.getTaskList()

    fun delTask(id: Int, onSuccess: () -> Unit, onError: (throwable: Throwable) -> Unit, onFinish: () -> Unit) {
        launchOnUITryCatch(
                {
                    onSuccess()
                    TaskRepository.delTask(id)
                },
                {
                    onError(it)
                    error(it)
                },
                { onFinish() },
                true)
    }
}