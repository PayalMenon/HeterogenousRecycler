package com.example.recycler.viewmodel

import android.app.Application
import androidx.lifecycle.*
import com.example.recycler.api.ApiRepository
import com.example.recycler.model.Article
import com.example.recycler.model.Articles
import com.example.recycler.util.Event
import com.example.recycler.util.NetworkResponse
import kotlinx.coroutines.launch
import javax.inject.Inject
import com.example.recycler.R

class SearchViewModel @Inject constructor(application: Application,
                                          private val apiRepository: ApiRepository) : AndroidViewModel(application) {
    //LiveData
    private val _showLoading = MutableLiveData<Event<Unit>>()
    val showLoading: LiveData<Event<Unit>>
        get() = _showLoading

    private val _hideLoading = MutableLiveData<Event<Unit>>()
    val hideLoading: LiveData<Event<Unit>>
        get() = _hideLoading

    private val _hideText = MutableLiveData<Event<Unit>>()
    val hideText: LiveData<Event<Unit>>
        get() = _hideText

    private val _showText = MutableLiveData<Event<Int>>()
    val showText: LiveData<Event<Int>>
        get() = _showText

    private val _showArticleList = MutableLiveData<Event<Unit>>()
    val showArticleList: LiveData<Event<Unit>>
        get() = _showArticleList

    private var articleList : List<Article>? = null

    fun onSearchSelected(searchQuery: String) {
        _hideText.value = Event(Unit)
        _showLoading.value = Event(Unit)
        viewModelScope.launch {
            when(val result :NetworkResponse<Articles> = apiRepository.getArticleList(searchQuery)) {
                is NetworkResponse.Success -> {
                    articleList = result.body.response.docs
                    _hideLoading.value = Event(Unit)
                    _hideText.value = Event(Unit)
                    _showArticleList.value = Event(Unit)
                }
                is NetworkResponse.Failure -> {
                    _hideLoading.value = Event(Unit)
                    _showText.value = Event(R.string.oops)
                }
            }
        }
    }

    fun getArticleList() : List<Article>? {
        return articleList
    }
}