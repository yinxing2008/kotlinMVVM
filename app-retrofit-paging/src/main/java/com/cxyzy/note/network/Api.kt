package com.cxyzy.note.network

import com.cxyzy.note.network.bean.Task
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface Api {
    @GET("cxyzy1/kotlinMVVM/master/data_task.json")
    fun getTaskAsync(@Query("page") page: Int, @Query("pageSize") pageSize: Int): Call<List<Task>>
}