package com.jy02.newsapp.main.newsArticle

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.jy02.newsapp.databinding.ActivityNewsArticleBinding
import com.jy02.newsapp.util.extension.loadImage
import dagger.hilt.android.AndroidEntryPoint
import org.mongodb.kbson.ObjectId
import javax.inject.Inject

@AndroidEntryPoint
class NewsArticleActivity : AppCompatActivity() {

    private lateinit var binding: ActivityNewsArticleBinding
    val viewModel: NewsArticleViewModel by viewModels()
    private lateinit var articleId: ObjectId
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNewsArticleBinding.inflate(layoutInflater)
        setContentView(binding.root)

        intent.extras?.getString("id")?.let {
            articleId = ObjectId(it)
        }

        binding.apply {
            viewModel.newsArticle.observe(this@NewsArticleActivity){ article ->
                article.urlToImage?.let { imgArticle.loadImage(this@NewsArticleActivity, it) }
                txvArticleTitle.text = article.title
                txvArticleContent.text = article.content
            }
            viewModel.getNews(articleId)
        }
    }
}