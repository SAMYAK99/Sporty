package com.projects.trending.sporty.adapters

import androidx.recyclerview.widget.DiffUtil
import com.projects.trending.sporty.models.Article

class ArticleDiffUtil(
    private val oldList: List<Article>,
    private val newList: List<Article>
): DiffUtil.Callback() {

    override fun getOldListSize(): Int {
        return oldList.size
    }

    override fun getNewListSize(): Int {
        return newList.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition] === newList[newItemPosition]
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].id == newList[newItemPosition].id
                && oldList[oldItemPosition].title == newList[newItemPosition].title
                && oldList[oldItemPosition].description == newList[newItemPosition].description
                && oldList[oldItemPosition].url == newList[newItemPosition].url
                && oldList[oldItemPosition].urlToImage == newList[newItemPosition].urlToImage
                && oldList[oldItemPosition].author == newList[newItemPosition].author
                && oldList[oldItemPosition].publishedAt == newList[newItemPosition].publishedAt
                && oldList[oldItemPosition].source == newList[newItemPosition].source
    }
}