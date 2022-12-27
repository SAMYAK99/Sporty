package com.projects.trending.sporty.data.local

import androidx.lifecycle.LiveData
import androidx.room.*
import com.projects.trending.sporty.models.Article

@Dao
interface NewsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert (article: Article) : Long

    @Query("SELECT * FROM articles")
    fun getAllArticles() : LiveData<List<Article>>

    @Delete
    suspend fun deleteArticle(article: Article)

    @Query("SELECT count(*) FROM articles WHERE url = :url")
    suspend  fun isDataExist(url : String): Int
}