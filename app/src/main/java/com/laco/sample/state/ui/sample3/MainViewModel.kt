package com.laco.sample.state.ui.sample3

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.laco.sample.state.domain.model.NewsItem
import com.laco.sample.state.domain.usecase.GetNewsItemListUseCase
import com.laco.sample.state.ui.model.NewsItemUiState
import com.laco.sample.state.ui.model.toUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class MainViewModel constructor(
    private val getNewsItemListUseCase: GetNewsItemListUseCase
) : ViewModel() {

    private val _state: MutableStateFlow<NewsUiState> =
        MutableStateFlow(NewsUiState.Uninitialized)

    val state: StateFlow<NewsUiState> = _state.asStateFlow()

    val isEmpty: StateFlow<Boolean> = state
        .map { it.isEmpty }
        .stateIn(viewModelScope, SharingStarted.Eagerly, false)

    val isError: StateFlow<Boolean> = state
        .map { it.isError }
        .stateIn(viewModelScope, SharingStarted.Eagerly, false)

    val isLoading: StateFlow<Boolean> = state
        .map { it.isLoading }
        .stateIn(viewModelScope, SharingStarted.Eagerly, false)

    // Fetch
    fun fetchNewsItems() {
        _state.update { state -> state.copy(isLoading = true, isError = false) }

        viewModelScope.launch {
            val result: Result<List<NewsItem>> = getNewsItemListUseCase()
            result
                .map { it.toUiState() }
                .onSuccess {
                    _state.value = NewsUiState(newsItems = it)
                }
                .onFailure {
                    _state.value = NewsUiState(isError = true)
                }
        }
    }

    fun toggleBookmark(item: NewsItemUiState) {
        _state.update { state ->
            val newsItems = state.newsItems.map {
                if (item.id == it.id) {
                    it.copy(bookmarked = !it.bookmarked)
                } else {
                    it
                }
            }
            state.copy(newsItems = newsItems)
        }

        // 이렇게도 작성 가능
        _state.update { state -> state.toggleBookmark(item.id) }
    }
}
