package com.example.mynewsapp.data.local

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.mynewsapp.data.remote.entity.Article

@Dao
interface NewsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert( article: Article): Long

    @Delete
    suspend fun delete(article: Article)

    @Query("select * from articles")
    fun getArticles(): LiveData<List<Article>>


}