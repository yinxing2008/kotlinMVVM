package com.cxyzy.note.db.dao

import androidx.paging.DataSource
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.cxyzy.note.network.bean.Task

@Dao
interface TaskDao {
    @Query("SELECT * FROM Task")
    fun getTaskList(): DataSource.Factory<Int, com.cxyzy.note.network.bean.Task>

    @Insert
    fun add(task: com.cxyzy.note.network.bean.Task)

    @Insert
    fun add(tasks: List<com.cxyzy.note.network.bean.Task>)

    @Delete
    fun del(task: com.cxyzy.note.network.bean.Task)
}