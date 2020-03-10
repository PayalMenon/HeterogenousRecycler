package com.example.recycler.api

import com.example.recycler.model.Articles
import com.example.recycler.util.NetworkResponse
import javax.inject.Inject

class ApiRepository @Inject constructor(private val apiService: ApiService) {

    suspend fun getArticleList(query: String) : NetworkResponse<Articles>{
        val result = apiService.getArticleList(query)
        return if(result.isSuccessful)  {
            val body = result.body()
            body?.let {
                NetworkResponse.Success(body)
            } ?: NetworkResponse.Failure(result)
        } else  {
            NetworkResponse.Failure(result)
        }
    }
}