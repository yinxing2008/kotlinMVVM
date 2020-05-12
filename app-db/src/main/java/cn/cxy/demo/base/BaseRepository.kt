package cn.cxy.demo.base

import cn.cxy.demo.db.AppDatabase

open class BaseRepository {
    val dbInstance = AppDatabase.getInstance()
}