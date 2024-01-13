package com.jy02.newsapp.data.models

data class NewsModel(
    var articles: List<Article>?,
    var status: String?,
    var totalResults: Int?
)