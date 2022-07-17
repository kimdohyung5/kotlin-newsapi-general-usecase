package com.example.mynewsapp.util

import com.example.mynewsapp.BuildConfig

object Constants {
    const val API_KEY = BuildConfig.NEWS_KEY
    const val BASE_URL = "https://newsapi.org"

    const val SEARCH_NEWS_TIME_DELAY = 500L
    const val QUERY_PAGE_SIZE = 20
}