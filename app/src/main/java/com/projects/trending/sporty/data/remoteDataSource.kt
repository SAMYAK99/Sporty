package com.projects.trending.sporty.data

import com.projects.trending.sporty.data.remote.ApiInterface
import javax.inject.Inject

class remoteDataSource @Inject constructor(private val newsApi : ApiInterface) {

    // RETROFIT
    suspend fun getBreakingNews(searchQuery: String, pageNumber: Int) =
        newsApi.getNews(searchQuery, pageNumber)
}