package com.cxyzy.demo.network

import com.cxyzy.demo.network.response.RepoResp
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface Api {
    @GET("/users/{user}/repos")
    suspend fun repos(@Path("user") user: String, @Query("page") page: Int, @Query("per_page") perPage: Int):List<RepoResp>
}