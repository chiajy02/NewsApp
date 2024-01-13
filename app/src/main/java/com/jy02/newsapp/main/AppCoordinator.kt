package com.jy02.newsapp.main

import android.content.Context
import android.content.Intent
import com.jy02.newsapp.main.newsArticle.NewsArticleActivity
import org.mongodb.kbson.ObjectId

class AppCoordinator {
    fun navigateToNewsArticle(context: Context, id: ObjectId){
        val intent = Intent(context, NewsArticleActivity::class.java)
        intent.putExtra("id", id.toHexString())
        context.startActivity(intent)
    }
}