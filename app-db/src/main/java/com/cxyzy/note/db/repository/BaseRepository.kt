package com.cxyzy.note.db.repository

import com.cxyzy.note.App
import com.cxyzy.note.db.AppDatabase

open class BaseRepository {
    val dbInstance = AppDatabase.getInstance(App.context)
}