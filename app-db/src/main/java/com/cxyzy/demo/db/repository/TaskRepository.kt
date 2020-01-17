package com.cxyzy.demo.db.repository

import androidx.paging.Config
import androidx.paging.toLiveData
import com.cxyzy.demo.db.AppDatabase
import com.cxyzy.demo.db.bean.Task
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

object TaskRepository : BaseRepository() {
    private val taskDao = dbInstance.taskDao()
    fun getTaskList() = taskDao.getTaskList().toLiveData(Config(
            pageSize = 30,
            enablePlaceholders = true))

    suspend fun add(name: String) {
        withContext(Dispatchers.IO) {
            val task = Task(0, name)
            taskDao.add(task)
        }
    }

    suspend fun delTask(id: Int) {
        withContext(Dispatchers.IO) {
            val task = Task(id, "")
            taskDao.del(task)
        }
    }
}