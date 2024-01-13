package com.jy02.newsapp.main

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.viewModels
import androidx.recyclerview.widget.RecyclerView
import com.jy02.newsapp.R
import com.jy02.newsapp.data.models.RealmArticle
import com.jy02.newsapp.databinding.RvItemNewsBinding
import com.jy02.newsapp.util.extension.loadImage

class NewsListAdapter: RecyclerView.Adapter<NewsListAdapter.NewsViewHolder>() {

    private var newsList = listOf<RealmArticle>()

    inner class NewsViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        private val binding = RvItemNewsBinding.bind(itemView)
        private val viewModel = (itemView.context as MainActivity).viewModel
        init {
            itemView.setOnClickListener {
                viewModel.deleteNewsLocal(newsList.get(position).id)
            }
        }
        fun bind(article: RealmArticle){
            binding.apply {
                article.urlToImage?.let { imgNewsItem.loadImage(itemView.context, it) }
                txvNewsItem.text = article.title
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        return NewsViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.rv_item_news, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return newsList.size
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        holder.bind(newsList.get(position))
    }

    fun setNews(newsList: List<RealmArticle>){
        this.newsList = newsList
        this.notifyDataSetChanged()
    }
}