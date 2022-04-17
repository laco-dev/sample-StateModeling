package com.laco.sample.state.ui.model

import androidx.recyclerview.widget.DiffUtil
import com.laco.sample.state.domain.model.NewsItem

data class NewsItemUiState(
    val id: String,
    val title: String,
    val body: String,
    val bookmarked: Boolean
) {

    class DiffCallback : DiffUtil.ItemCallback<NewsItemUiState>() {
        override fun areItemsTheSame(
            oldItem: NewsItemUiState,
            newItem: NewsItemUiState
        ): Boolean = oldItem.id == newItem.id

        override fun areContentsTheSame(
            oldItem: NewsItemUiState,
            newItem: NewsItemUiState
        ): Boolean = oldItem == newItem
    }
}

fun List<NewsItem>.toUiState(): List<NewsItemUiState> = map { it.toUiState() }

fun NewsItem.toUiState(): NewsItemUiState = NewsItemUiState(
    id = id,
    title = title,
    body = body,
    bookmarked = bookmarked
)
