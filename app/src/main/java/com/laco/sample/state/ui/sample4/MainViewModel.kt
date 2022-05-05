package com.laco.sample.state.ui.sample4

import androidx.lifecycle.viewModelScope
import com.laco.sample.state.domain.usecase.GetNewsItemListUseCase
import com.laco.sample.state.ui.model.NewsItemUiState
import com.laco.sample.state.ui.model.toUiState
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class MainViewModel constructor(
    private val getNewsItemListUseCase: GetNewsItemListUseCase
) : BaseViewModel<List<NewsItemUiState>>(UiState.loading()) {

    val newsItems: StateFlow<List<NewsItemUiState>> = state
        .map { it.getOrDefault(emptyList()) }
        .stateIn(viewModelScope, SharingStarted.Eagerly, emptyList())

    val isEmpty: StateFlow<Boolean> = state.filter { !it.isLoading }
        .map { it.value?.isEmpty() == true }
        .stateIn(viewModelScope, SharingStarted.Eagerly, false)

    // Fetch
    fun fetchNewsItems() {
        updateState(isLoading = true)

        viewModelScope.launch {
            getNewsItemListUseCase()
                .map { it.toUiState() }
                .onSuccess { value ->
                    updateState { value }
                }
                .onFailure {
                    updateState(isLoading = false, cause = it)
                }
        }
    }

    // Event
    fun toggleBookmark(item: NewsItemUiState) = updateState { value ->
        value.map {
            if (it.id == item.id) {
                it.copy(bookmarked = !it.bookmarked)
            } else {
                it
            }
        }
    }
}
