package com.laco.sample.state.ui.sample4

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
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class MainViewModel constructor(
    private val getNewsItemListUseCase: GetNewsItemListUseCase
) : ViewModel() {

    private val _state: MutableStateFlow<UiState<List<NewsItemUiState>>> =
        MutableStateFlow(UiState.loading())
    val state = _state.asStateFlow()

    val newsItems: StateFlow<List<NewsItemUiState>> = state
        .map { it.getOrDefault(emptyList()) }
        .stateIn(viewModelScope, SharingStarted.Eagerly, emptyList())

    val isEmpty: StateFlow<Boolean> = state.filter { !it.isLoading }
        .map { it.value?.isEmpty() == true }
        .stateIn(viewModelScope, SharingStarted.Eagerly, false)

    // Fetch
fun fetchNewsItems() {
    _state.update { it.copy(isLoading = true) }

    viewModelScope.launch {
        val result: Result<List<NewsItem>> = getNewsItemListUseCase()
        result
            .map { it.toUiState() }
            .onSuccess { value ->
                _state.value = UiState.success(value)
            }
            .onFailure {
                _state.value = UiState.error(it)
            }
    }
}

    // Event
    fun toggleBookmark(item: NewsItemUiState) {
        val value = state.value.update { value ->
            value.map {
                if (it.id == item.id) {
                    it.copy(bookmarked = !it.bookmarked)
                } else {
                    it
                }
            }
        }
        _state.value = value
    }
}
