package com.cxyzy.demo.db.repository

import com.cxyzy.demo.db.AppDatabase

open class BaseRepository {
    val dbInstance = AppDatabase.getInstance()
}