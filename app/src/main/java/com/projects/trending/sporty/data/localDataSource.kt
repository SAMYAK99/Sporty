package com.projects.trending.sporty.data

import com.projects.trending.sporty.data.local.NewsDao
import com.projects.trending.sporty.models.Article
import javax.inject.Inject

class localDataSource @Inject constructor(private val newsDao: NewsDao) {

    // ROOM
    suspend fun upsert(article: Article) = newsDao.upsert(article)

    fun getSavedNews() = newsDao.getAllArticles()

    suspend fun deleteArticle(article: Article) =newsDao.deleteArticle(article)
}