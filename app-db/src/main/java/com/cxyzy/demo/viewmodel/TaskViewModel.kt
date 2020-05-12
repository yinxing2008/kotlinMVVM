package com.cxyzy.demo.viewmodel

import com.cxyzy.demo.db.repository.TaskRepository

class TaskViewModel : BaseViewModel() {
    val taskList = TaskRepository.getTaskList()

    fun delTask(id: Int, onSuccess: () -> Unit, onError: (throwable: Throwable) -> Unit, onFinish: () -> Unit) {
        launch(block = { TaskRepository.delTask(id) })
    }
}