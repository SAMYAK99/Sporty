package com.projects.trending.sporty.models


import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class NewsModel(
    @SerializedName("articles")
    val articles: List<Article>,
    @SerializedName("status")
    val status: String,
    @SerializedName("totalResults")
    val totalResults: Int
)