package com.jy02.newsapp.data.network

import com.jy02.newsapp.data.models.NewsModel
import retrofit2.Response
import javax.inject.Inject

class NewsRepository @Inject constructor(
    private val newsService: NewsService
) {

    suspend fun fetchNews(): Response<NewsModel>{
        return newsService.getNews()
    }

}