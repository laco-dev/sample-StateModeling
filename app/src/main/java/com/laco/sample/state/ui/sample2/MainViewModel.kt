package com.laco.sample.state.ui.sample2

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.laco.sample.state.domain.model.NewsItem
import com.laco.sample.state.domain.usecase.GetNewsItemListUseCase
import com.laco.sample.state.ui.model.NewsItemUiState
import com.laco.sample.state.ui.model.toUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class MainViewModel constructor(
    private val getNewsItemListUseCase: GetNewsItemListUseCase
) : ViewModel() {

    private val _state: MutableStateFlow<NewsUiState> = MutableStateFlow(NewsUiState.Uninitialized)
    val state: StateFlow<NewsUiState> = _state.asStateFlow()

    fun fetchNewsItems() {
        _state.update { state ->
            NewsUiState.Loading(state.newsItems)
        }

        viewModelScope.launch {
            val result: Result<List<NewsItem>> = getNewsItemListUseCase()
            result
                .map { it.toUiState() }
                .onSuccess {
                    _state.value = NewsUiState.Success(it)
                }
                .onFailure {
                    _state.value = NewsUiState.Error
                }
        }
    }


    // Event
    fun toggleBookmark(item: NewsItemUiState) {
        val state = state.value
        if (state is NewsUiState.Success) {
            val newsItems = state.newsItems.map {
                if (item.id == it.id) {
                    it.copy(bookmarked = !it.bookmarked)
                } else {
                    it
                }
            }
            _state.value = state.copy(newsItems = newsItems)
        }
    }
}
