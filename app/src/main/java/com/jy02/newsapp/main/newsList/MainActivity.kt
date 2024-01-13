package com.jy02.newsapp.main.newsList

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.jy02.newsapp.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import androidx.activity.viewModels

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    val viewModel: NewsViewModel by viewModels()
    private var newsListAdapter = NewsListAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)

        binding.apply {
            rvNewsList.setHasFixedSize(true)
            rvNewsList.layoutManager = LinearLayoutManager(this@MainActivity)
            rvNewsList.adapter = newsListAdapter

            viewModel.newsArticle.observe(this@MainActivity){
                it?.let { it -> newsListAdapter.setNews(it) }
            }
        }

    }
}