package com.jy02.newsapp.data.local

import com.jy02.newsapp.data.models.RealmArticle
import com.jy02.newsapp.data.models.Article
import kotlinx.coroutines.flow.Flow
import org.mongodb.kbson.ObjectId

interface DatabaseNewsRepository {
    fun getData(): Flow<List<RealmArticle>>
    fun filterData (author: String): Flow<List<RealmArticle>>
    suspend fun insertArticles(article: List<Article>)
    suspend fun updateArticle(article: RealmArticle)
    suspend fun deleteArticle(id: ObjectId)

}