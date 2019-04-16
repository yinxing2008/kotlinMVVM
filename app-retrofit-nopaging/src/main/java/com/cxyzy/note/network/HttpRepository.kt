package com.cxyzy.note.network

import com.cxyzy.note.utils.PAGE_SIZE

object HttpRepository : BaseHttpRepository() {
    suspend fun getTask() = api.repos(user = "cxyzy1", perPage = 1, page = PAGE_SIZE).await()
}