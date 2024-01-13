package com.jy02.newsapp.main.newsArticle

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.jy02.newsapp.data.local.DatabaseNewsRepository
import com.jy02.newsapp.data.models.RealmArticle
import dagger.hilt.android.lifecycle.HiltViewModel
import org.mongodb.kbson.ObjectId
import javax.inject.Inject

@HiltViewModel
class NewsArticleViewModel @Inject constructor(
    private val databaseNewsRepository: DatabaseNewsRepository
): ViewModel() {

    val newsArticle = MutableLiveData<RealmArticle>()

    fun getNews(id: ObjectId){
        newsArticle.value = databaseNewsRepository.getData(id)
    }

}