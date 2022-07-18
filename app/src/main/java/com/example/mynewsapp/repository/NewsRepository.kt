package com.example.mynewsapp.repository

import com.example.mynewsapp.data.local.NewsDatabase
import com.example.mynewsapp.data.remote.NewsAPI
import com.example.mynewsapp.data.remote.entity.Article
import javax.inject.Inject


class NewsRepository @Inject constructor(
    private val db: NewsDatabase,
    private val api: NewsAPI
) {
    suspend fun getBreakingNews(countryCode: String, pageNumber: Int) =
        api.getBreakingNews(countryCode, pageNumber)

    suspend fun searchNews(searchQuery: String, pageNumber: Int) =
        api.searchForNews(searchQuery, pageNumber)

    suspend fun upsert(article: Article) = db.getDao().upsert(article)

    suspend fun delete(article: Article) = db.getDao().delete(article)

    fun getSavedNews() = db.getDao().getArticles()

}