package com.cxyzy.note

import com.cxyzy.note.db.AppDatabase
import com.cxyzy.note.db.repository.TaskRepository

object InjectionUtil {
    private val dbInstance = AppDatabase.getInstance(App.context)

    fun getTaskRepository(): TaskRepository = TaskRepository.getInstance(dbInstance.taskDao())

}