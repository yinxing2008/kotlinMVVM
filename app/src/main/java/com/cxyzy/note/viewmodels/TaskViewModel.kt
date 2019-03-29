package com.cxyzy.note.viewmodels

import androidx.lifecycle.ViewModel
import com.cxyzy.note.db.repository.TaskRepository
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class TaskViewModel internal constructor(private val taskRepository: TaskRepository) : ViewModel() {
    val taskList = taskRepository.getTaskList()

    fun delTask(id: Int) {
        GlobalScope.launch {
            taskRepository.delTask(id)
        }
    }
}