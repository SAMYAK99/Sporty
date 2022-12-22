package com.projects.trending.sporty.remote

import com.projects.trending.sporty.models.NewsModel
import com.projects.trending.sporty.utils.Constants
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {

    @GET("v2/everything")
    suspend fun getNews(
        @Query("q")
        searchQuery: String,
        @Query("page")
        pageNumber: Int = 1,
        @Query("apiKey")
        apiKey: String = Constants.apiKey
    ): Response<NewsModel>


}