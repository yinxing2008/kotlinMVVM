package com.cxyzy.note.network

import com.cxyzy.note.network.bean.Task
import kotlinx.coroutines.Deferred
import retrofit2.http.GET

interface Api {
    @GET("cxyzy1/kotlinMVVM/master/data_task.json")
    fun getTask(): Deferred<List<Task>>
}