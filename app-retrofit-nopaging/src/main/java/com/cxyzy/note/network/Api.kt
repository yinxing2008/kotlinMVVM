package com.cxyzy.note.network

import com.cxyzy.note.network.bean.Repo
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface Api {
    @GET("/users/{user}/repos")
    fun repos(@Path("user") user: String, @Query("page") page: Int, @Query("per_page") perPage: Int): Deferred<List<Repo>>
}