package com.laco.sample.state.domain.model

data class NewsItem(
    val id: String,
    val title: String,
    val body: String,
    val bookmarked: Boolean
)
