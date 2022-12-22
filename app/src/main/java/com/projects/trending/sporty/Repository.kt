package com.projects.trending.sporty

import com.projects.trending.sporty.remote.ApiUtilites

class Repository {

    suspend fun getBreakingNews(searchQuery: String, pageNumber: Int) =
        ApiUtilites.service.getNews(searchQuery, pageNumber)
}