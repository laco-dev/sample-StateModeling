package com.laco.sample.state.ui.sample4

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

abstract class BaseViewModel<T>(
    initialState: UiState<T>
) : ViewModel() {

    private val _state = MutableStateFlow(initialState)
    val state = _state.asStateFlow()

    fun updateState(transform: (T) -> T) {
        val state = state.value
        val value = state.value ?: return
        _state.update { UiState.success(transform(value)) }
    }
}

