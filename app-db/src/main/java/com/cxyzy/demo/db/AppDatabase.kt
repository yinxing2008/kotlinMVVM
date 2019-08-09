package com.cxyzy.demo.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import com.cxyzy.demo.db.bean.Task
import com.cxyzy.demo.db.dao.TaskDao
import com.cxyzy.demo.db.test.DbTestDataInit
import com.cxyzy.demo.utils.DATABASE_NAME


@Database(entities = [Task::class], version = 1, exportSchema = false)

abstract class AppDatabase : RoomDatabase() {
    abstract fun taskDao(): TaskDao

    companion object {

        // For Singleton instantiation
        @Volatile
        private var instance: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            return instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also { instance = it }
            }
        }

        private fun buildDatabase(context: Context): AppDatabase {
            return Room.databaseBuilder(context, AppDatabase::class.java, DATABASE_NAME)
                    .addCallback(object : RoomDatabase.Callback() {
                        override fun onCreate(db: SupportSQLiteDatabase) {
                            super.onCreate(db)
                            val request = OneTimeWorkRequestBuilder<DbTestDataInit>().build()
                            WorkManager.getInstance().enqueue(request)
                        }
                    })
                    .build()
        }
    }
}
