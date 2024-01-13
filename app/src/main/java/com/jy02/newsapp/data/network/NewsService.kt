package com.jy02.newsapp.data.network

import com.jy02.newsapp.data.models.NewsModel
import com.jy02.newsapp.util.Constants.Companion.API_KEY
import retrofit2.Response
import retrofit2.http.GET

interface NewsService {
    @GET("/v2/top-headlines?country=us&apiKey=$API_KEY")
    suspend fun getNews(): Response<NewsModel>

}