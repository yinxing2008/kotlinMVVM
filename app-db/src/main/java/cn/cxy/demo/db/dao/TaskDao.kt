package cn.cxy.demo.db.dao

import androidx.paging.DataSource
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import cn.cxy.demo.db.bean.Task

@Dao
interface TaskDao {
    @Query("SELECT * FROM Task")
    fun getTaskList(): DataSource.Factory<Int, Task>

    @Insert
    fun add(task: Task)

    @Insert
    fun add(tasks: List<Task>)

    @Delete
    fun del(task: Task)
}