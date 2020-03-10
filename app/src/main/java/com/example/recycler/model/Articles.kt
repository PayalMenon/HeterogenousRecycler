package com.example.recycler.model

import com.google.gson.annotations.SerializedName

data class Articles(
    val response: Docs
)

data class Docs(
    val docs: List<Article>
)

data class Article(
    @SerializedName("_id")
    val id: String,
    val headline: Headline,
    val uri: String,
    @SerializedName("web_url")
    val webUrl: String,
    @SerializedName("subsection_name")
    val subSectionName: String,
    @SerializedName("lead_paragraph")
    val leadParagraph: String,
    val abstract: String,
    @SerializedName("pub_date")
    val publishedOn: String,
    val multimedia: List<MultiMedia>,
    @SerializedName("byline")
    val author: Author
)

data class Headline(
    @SerializedName("main")
    val headlineMain: String
)

data class MultiMedia(
    val type: String,
    @SerializedName("url")
    val imageUrl: String,
    val subType: String
)

data class Author(
    @SerializedName("original")
    val author: String
)