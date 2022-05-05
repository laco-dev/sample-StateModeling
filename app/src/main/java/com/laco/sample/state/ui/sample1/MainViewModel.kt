package com.laco.sample.state.ui.sample1

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

    private val _isLoading: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    private val _isError: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val isError: StateFlow<Boolean> = _isError.asStateFlow()

    private val _isEmpty: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val isEmpty: StateFlow<Boolean> = _isEmpty.asStateFlow()

    private val _newsItems = MutableStateFlow<List<NewsItemUiState>>(emptyList())
    val newsItems = _newsItems.asStateFlow()

    // Fetch
    fun fetchNewsItems() {
        _isLoading.value = true
        _isError.value = false

        viewModelScope.launch {
            val result: Result<List<NewsItem>> = getNewsItemListUseCase()
            result
                .map { it.toUiState() }
                .onSuccess {
                    _isLoading.value = false
                    _newsItems.value = it
                    _isEmpty.value = it.isEmpty()
                }
                .onFailure {
                    _isLoading.value = false
                    _isError.value = true
                }
        }
    }

    fun toggleBookmark(item: NewsItemUiState) {
        _newsItems.update { items ->
            items.map {
                if (item.id == it.id) {
                    it.copy(bookmarked = !it.bookmarked)
                } else {
                    it
                }
            }
        }
    }
}
