package com.cxyzy.note.db.repository

import androidx.paging.Config
import androidx.paging.toLiveData
import com.cxyzy.note.db.bean.Task
import com.cxyzy.note.db.dao.TaskDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class TaskRepository private constructor(private val taskDao: TaskDao) {

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

    companion object {
        @Volatile
        private var instance: TaskRepository? = null

        fun getInstance(taskDao: TaskDao) =
                instance ?: synchronized(this) {
                    instance
                            ?: TaskRepository(taskDao).also { instance = it }
                }
    }
}