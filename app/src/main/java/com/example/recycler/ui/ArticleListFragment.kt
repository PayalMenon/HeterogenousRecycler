package com.example.recycler.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders
import com.example.recycler.R
import com.example.recycler.dagger.ViewModelFactory
import com.example.recycler.model.Article
import com.example.recycler.viewmodel.SearchViewModel
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.list_fragment.view.*
import javax.inject.Inject

class ArticleListFragment : DaggerFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    private lateinit var searchViewModel : SearchViewModel
    private lateinit var adapter: ArticleListAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        searchViewModel = ViewModelProviders.of(requireActivity(), viewModelFactory)
            .get(SearchViewModel::class.java)
        val view = inflater.inflate(R.layout.list_fragment, container, false)
        adapter = ArticleListAdapter { article: Article -> onArticleClicked(article) }
        view.article_list.adapter = adapter
        setArticleList(view)
        return view
    }

    private fun setArticleList(view: View) {
        view.article_list.visibility = View.VISIBLE
        adapter.setArticleList(searchViewModel.getArticleList()?: listOf())
    }

    private fun onArticleClicked(article: Article) {
        // open details view
    }
}