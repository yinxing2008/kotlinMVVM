package cn.cxy.demo.viewmodel

import cn.cxy.demo.base.BaseViewModel
import cn.cxy.demo.db.repository.TaskRepository

class TaskViewModel : BaseViewModel() {
    private val taskRepository by lazy { TaskRepository() }

    val taskList = taskRepository.getTaskList()

    fun delTask(id: Int, onSuccess: () -> Unit, onError: (throwable: Throwable) -> Unit, onFinish: () -> Unit) {
        launch(block = { taskRepository.delTask(id) })
    }
}