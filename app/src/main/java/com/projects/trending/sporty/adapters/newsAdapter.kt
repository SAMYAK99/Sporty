package com.projects.trending.sporty.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.projects.trending.sporty.R
import com.projects.trending.sporty.databinding.ItemNewsBinding
import com.projects.trending.sporty.models.Article

class newsAdapter(private val context: Context, private val list: List<Article>) :
    RecyclerView.Adapter<newsAdapter.NewsViewHolder>() {


    inner class NewsViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var binding = ItemNewsBinding.bind(view)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        return NewsViewHolder(
            LayoutInflater.from(context).inflate(R.layout.item_news, parent, false)
        )
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val item = list[position]

        holder.binding.apply {
            Glide.with(context)
                .load(item.urlToImage)
                .thumbnail(Glide.with(context).load(R.drawable.spinner))
                .into(this.newsImageView)

            this.newsHeadingTextView.text = item.title
            this.publishedTimeTextview.text = item.publishedAt
            this.publisherSourceTextView.text = item.author
        }

    }

    override fun getItemCount(): Int {
        return list.size
    }
}
