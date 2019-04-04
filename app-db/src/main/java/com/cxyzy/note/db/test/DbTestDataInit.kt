package com.cxyzy.note.db.test

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.cxyzy.note.db.AppDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope

class DbTestDataInit(
        context: Context,
        workerParams: WorkerParameters
) : CoroutineWorker(context, workerParams) {

    override val coroutineContext = Dispatchers.IO

    override suspend fun doWork(): Result = coroutineScope {

        val database = AppDatabase.getInstance(applicationContext)

        val mList = mutableListOf<com.cxyzy.note.network.bean.Task>()

        (1..1000).forEach {
            mList.add(com.cxyzy.note.network.bean.Task(it, "task$it"))
        }
        database.taskDao().add(mList)
        Result.success()
    }
}