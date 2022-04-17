package com.laco.sample.state.ui.sample3

import com.laco.sample.state.ui.model.NewsItemUiState

data class NewsUiState(
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val newsItems: List<NewsItemUiState> = emptyList()
) {

    val isEmpty: Boolean = !isLoading && newsItems.isEmpty()

    companion object {
        val Uninitialized = NewsUiState(
            isLoading = false,
            isError = false,
            newsItems = emptyList()
        )
    }
}
