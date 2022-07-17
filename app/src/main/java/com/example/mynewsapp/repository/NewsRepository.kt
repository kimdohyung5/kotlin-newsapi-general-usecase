package com.example.mynewsapp.repository

import com.example.mynewsapp.data.local.NewsDatabase
import com.example.mynewsapp.data.remote.RetrofitInstance
import com.example.mynewsapp.data.remote.entity.Article

class NewsRepository(
    val db: NewsDatabase
) {
    suspend fun getBreakingNews(countryCode: String, pageNumber: Int) =
        RetrofitInstance.api.getBreakingNews(countryCode, pageNumber)

    suspend fun searchNews(searchQuery: String, pageNumber: Int) =
        RetrofitInstance.api.searchForNews(searchQuery, pageNumber)

    suspend fun upsert(article: Article) = db.getDao().upsert(article)

    suspend fun delete(article: Article) = db.getDao().delete(article)

    fun getSavedNews() = db.getDao().getArticles()

}