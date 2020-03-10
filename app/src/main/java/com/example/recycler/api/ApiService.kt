package com.example.recycler.api

import com.example.recycler.model.Articles
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("svc/search/v2/articlesearch.json")
    suspend fun getArticleList(
        @Query("q") query: String
    ): Response<Articles>
}