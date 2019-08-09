package com.cxyzy.demo.network

import com.cxyzy.demo.utils.PAGE_SIZE
import com.cxyzy.note.network.BaseHttpRepository

object HttpRepository : BaseHttpRepository() {
    suspend fun getRepo() = api.repos(user = "cxyzy1", page = 1, perPage = PAGE_SIZE)
}