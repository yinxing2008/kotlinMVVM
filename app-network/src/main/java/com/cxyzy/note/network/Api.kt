package com.tanzhiqiang.kmvvm.repository

import com.cxyzy.note.network.bean.Task
import kotlinx.coroutines.Deferred
import retrofit2.http.GET

interface Api {
    /**
     * 获取天气信息
     */
    @GET("cxyzy1/kotlinMVVM/master/data_task.json")
    fun getTask(): Deferred<List<Task>>
}