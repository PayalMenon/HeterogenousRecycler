package com.example.recycler.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.recycler.R
import com.example.recycler.model.Article
import com.example.recycler.model.MultiMedia
import com.example.recycler.ui.ArticleListAdapter.Companion.IMAGE_BASE_URL
import com.example.recycler.ui.ArticleListAdapter.Companion.IMAGE_TYPE
import kotlinx.android.synthetic.main.article_item.view.*
import kotlinx.android.synthetic.main.article_item.view.article_image
import kotlinx.android.synthetic.main.article_item.view.article_parent
import kotlinx.android.synthetic.main.article_item.view.article_title
import kotlinx.android.synthetic.main.article_item_2.view.*

class ArticleListAdapter(val clickListener : (Article) -> Unit) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        const val IMAGE_BASE_URL = "https://www.nytimes.com/"
        const val IMAGE_TYPE = "image"

        const val ITEM_TYPE_1 = 1
        const val ITEM_TYPE_2 = 2

    }

    var articles : List<Article> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if(viewType == ITEM_TYPE_1){
            ArticleViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.article_item, parent, false))
        } else {
            ArticleViewHolder2(LayoutInflater.from(parent.context).inflate(R.layout.article_item_2, parent, false))
        }
    }

    override fun getItemCount(): Int {
        return articles.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder.itemViewType) {
            ITEM_TYPE_1 ->{
                (holder as ArticleViewHolder).setArticleView(articles[position], clickListener)
            }
            ITEM_TYPE_2 -> {
                (holder as ArticleViewHolder2).setArticleView(articles[position], clickListener)
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if(position %2 == 0) {
            ITEM_TYPE_1
        } else {
            ITEM_TYPE_2
        }
    }

    fun setArticleList(articleList : List<Article>) {
        articles = articleList
    }
}

class ArticleViewHolder(val view: View) : RecyclerView.ViewHolder(view) {

    fun setArticleView(article: Article, clickListener: (Article) -> Unit) {
        view.article_title.text = article.headline.headlineMain
        val imageUrl: List<MultiMedia> = article.multimedia.filter { it.type == IMAGE_TYPE }
        if (imageUrl.isNotEmpty()) {
            Glide.with(view.context).load(IMAGE_BASE_URL.plus(imageUrl[0].imageUrl))
                .into(view.article_image)
        }
        view.article_parent.setOnClickListener { clickListener(article) }
    }
}

class ArticleViewHolder2(val view: View) : RecyclerView.ViewHolder(view) {

    fun setArticleView(article: Article, clickListener: (Article) -> Unit) {
        view.article_title.text = article.headline.headlineMain
        val imageUrl: List<MultiMedia> = article.multimedia.filter { it.type == IMAGE_TYPE }
        if (imageUrl.isNotEmpty()) {
            Glide.with(view.context).load(IMAGE_BASE_URL.plus(imageUrl[0].imageUrl))
                .into(view.article_image)
        }
        view.article_author.text = article.author.author
        view.article_parent.setOnClickListener { clickListener(article) }
    }
}