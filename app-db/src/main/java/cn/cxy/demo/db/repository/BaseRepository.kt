package cn.cxy.demo.db.repository

import cn.cxy.demo.db.AppDatabase

open class BaseRepository {
    val dbInstance = AppDatabase.getInstance()
}