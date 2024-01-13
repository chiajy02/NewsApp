package com.jy02.newsapp.data.local

import android.util.Log
import com.jy02.newsapp.data.models.RealmArticle
import com.jy02.newsapp.data.models.RealmSource
import com.jy02.newsapp.data.models.Article
import io.realm.kotlin.Realm
import io.realm.kotlin.ext.query
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import org.mongodb.kbson.ObjectId

class DatabaseNewsRepositoryImpl(val realm: Realm): DatabaseNewsRepository {
    override fun getData(): Flow<List<RealmArticle>> {
        return realm.query<RealmArticle>().asFlow().map { it.list }
    }

    override fun filterData(author: String): Flow<List<RealmArticle>> {
        return realm.query<RealmArticle>(query = "author CONTAINS[c] $0", author).asFlow().map { it.list }
    }

    override suspend fun insertArticles(article: List<Article>) {
        article.forEach {
            Log.d("insertArticles", "${it}")
            val realmArticle = RealmArticle().apply {
                author = it.author
                content = it.content
                description = it.description
                publishedAt = it.publishedAt
                source = RealmSource().apply {
                    id= it.source?.id
                    name= it.source?.name
                }
                title = it.title
                url = it.url
                urlToImage = it.urlToImage
            }

            realm.write {
                val existingArticle = query<RealmArticle>(query = "content==$0", it.content).first().find()
                if(existingArticle==null) copyToRealm(realmArticle)
            }
        }
    }

    override suspend fun updateArticle(article: RealmArticle) {
        realm.write {
            var queriedArticle  = query<RealmArticle>(query = "id==$0", article.id).first().find()
            queriedArticle = article
        }
    }

    override suspend fun deleteArticle(id: ObjectId) {
        realm.write {
            var article  = query<RealmArticle>(query = "id==$0", id).first().find()
            try{
                article?.let { delete(it) }
            }catch (e: Exception){
                Log.d("TAG", "${e.message}")
            }
        }
    }
}