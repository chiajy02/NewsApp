package com.jy02.newsapp.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jy02.newsapp.data.local.DatabaseNewsRepository
import com.jy02.newsapp.data.models.RealmArticle
import com.jy02.newsapp.data.network.NewsRepository
import com.jy02.newsapp.data.models.NewsModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import org.mongodb.kbson.ObjectId
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(
    private val newsRepository: NewsRepository,
    private val databaseNewsRepository: DatabaseNewsRepository
): ViewModel() {

    init {
        getNews()
        getNewsLocal()
    }

    val newsResponse = MutableLiveData<Response<NewsModel>>()
    var newsArticle = MutableLiveData<MutableList<RealmArticle>>()

    fun getNews(){
        viewModelScope.launch {
            val response = newsRepository.fetchNews()
            newsResponse.value = response
            newsResponse.value?.body()?.articles?.let {
                databaseNewsRepository.insertArticles(it)
            }

        }
    }

    fun getNewsLocal(){
        viewModelScope.launch {
            val response = databaseNewsRepository.getData().collect{
                newsArticle.value = it.toMutableList()
            }
        }
    }

    fun deleteNewsLocal(id: ObjectId){
        viewModelScope.launch {
            databaseNewsRepository.deleteArticle(id)
            newsArticle.value?.removeIf { it.id == id }
        }
    }

}