package com.cxyzy.demo.db.test

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.cxyzy.demo.db.AppDatabase
import com.cxyzy.demo.db.bean.Task
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope

class DbTestDataInit(
        context: Context,
        workerParams: WorkerParameters
) : CoroutineWorker(context, workerParams) {

    override val coroutineContext = Dispatchers.IO

    override suspend fun doWork(): Result = coroutineScope {

        val database = AppDatabase.getInstance()

        val mList = mutableListOf<Task>()

        (1..1000).forEach {
            mList.add(Task(it, "task$it"))
        }
        database.taskDao().add(mList)
        Result.success()
    }
}