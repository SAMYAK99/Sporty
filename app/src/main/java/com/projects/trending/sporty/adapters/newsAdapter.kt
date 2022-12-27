package com.projects.trending.sporty.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.projects.trending.sporty.R
import com.projects.trending.sporty.databinding.AdLayoutBinding
import com.projects.trending.sporty.databinding.ItemNewsBinding
import com.projects.trending.sporty.models.Ads
import com.projects.trending.sporty.models.Article
import com.projects.trending.sporty.views.HomeFragmentDirections
import java.util.*


class newsAdapter(val context: Context) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var list = Collections.emptyList<Article>()

    inner class newsViewHolder(private val newsView: ItemNewsBinding) :
        RecyclerView.ViewHolder(newsView.root) {
        fun bind(mList : Article) {
            newsView.apply {
                Glide.with(context)
                    .load(mList.urlToImage)
                    .thumbnail(Glide.with(context).load(R.drawable.spinner))
                    .into(this.newsImageView)

                this.newsHeadingTextView.text = mList.title
                this.publishedTimeTextview.text = mList.publishedAt
                this.publisherSourceTextView.text = mList.author
            }

            itemView.setOnClickListener {
                Navigation.findNavController(it).navigate(
                    HomeFragmentDirections.actionHomeFragmentToNewsDetailFragment(mList))
            }
        }

    }


    inner class adsViewHolder(private val adsView: AdLayoutBinding) :
        RecyclerView.ViewHolder(adsView.root) {
        fun bind(mList: Article) {
            adsView.apply {
                Glide.with(context)
                    .load(R.drawable.sp)
                    .thumbnail(Glide.with(context).load(R.drawable.spinner))
                    .into(this.adsImageView)

                adsHeadingTextView.text = context.getString(R.string.sportsText)
                adsTextView.text = context.getString(R.string.adText)
            }
        }
    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == 1) {
            val view =
                ItemNewsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            newsViewHolder(view)
        } else {
            val view =
                AdLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
           adsViewHolder(view)
        }
    }



    override fun getItemCount(): Int {
        return list.size
    }

    override fun getItemViewType(position: Int): Int {
        return if ((position+1) % 3 != 0) 1 else 0
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (getItemViewType(position) == 1) {
            (holder as newsAdapter.newsViewHolder).bind(list[position])
        } else {
            (holder as newsAdapter.adsViewHolder).bind(list[position])
        }
    }

    fun setData(toDoData: List<Article>){
        val toDoDiffUtil = ArticleDiffUtil(list, toDoData)
        val toDoDiffResult = DiffUtil.calculateDiff(toDoDiffUtil)
        this.list = toDoData
        toDoDiffResult.dispatchUpdatesTo(this)
    }
}