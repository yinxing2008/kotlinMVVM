package com.cxyzy.demo.network

import com.cxyzy.demo.network.bean.Repo
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface Api {
    @GET("/users/{user}/repos")
    fun repos(@Path("user") user: String, @Query("page") page: Int, @Query("per_page") perPage: Int): Call<List<Repo>>
}