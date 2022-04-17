package com.laco.sample.state.ui.sample2

import com.laco.sample.state.ui.model.NewsItemUiState

sealed class NewsUiState {

    open val newsItems: List<NewsItemUiState> = emptyList()

    val isLoading: Boolean get() = this is Loading
    val isError: Boolean get() = this == Error
    val isEmpty: Boolean get() = this == Empty

    object Uninitialized : NewsUiState()
    object Error : NewsUiState()
    object Empty : NewsUiState()

    data class Loading(
        override val newsItems: List<NewsItemUiState>
    ) : NewsUiState()

    data class Success(
        override val newsItems: List<NewsItemUiState>
    ) : NewsUiState()
}
