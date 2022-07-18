package com.example.mynewsapp.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncDifferConfig
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.mynewsapp.data.remote.entity.Article
import com.example.mynewsapp.databinding.ItemArticlePreviewBinding

class NewsAdapter : RecyclerView.Adapter<NewsAdapter.MyViewHolder>() {

    inner class MyViewHolder(val binding:ItemArticlePreviewBinding): RecyclerView.ViewHolder(binding.root)

    private val differCallback = object: DiffUtil.ItemCallback<Article>(){
        override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem.url == newItem.url
        }
        override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, differCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val inflater = LayoutInflater.from( parent.context )
        return MyViewHolder(ItemArticlePreviewBinding.inflate( inflater, parent, false ))
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val article = differ.currentList[position]
        holder.binding.apply {
            Glide.with(this.root).load(article.urlToImage).into(ivArticleImage)
            tvSource.text = article.source?.name
            tvTitle.text = article.description
            tvPublishedAt.text = article.publishedAt
            root.setOnClickListener {
                onItemClickListener?.let { it(article)}
            }
        }
    }

    private var onItemClickListener:((Article) -> Unit)? = null

    fun setOnItemClickListener(listener: (Article) -> Unit) {
        onItemClickListener = listener
    }


    override fun getItemCount(): Int {
        return differ.currentList.size
    }
}