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

    protected fun updateState(transform: (T) -> T) {
        val state = state.value
        val value = state.value ?: return
        _state.update {
            UiState.success(transform(value))
        }
    }

    protected fun updateState(
        isLoading: Boolean = state.value.isLoading,
        cause: Throwable? = state.value.cause,
        value: T? = state.value.value
    ) {
        _state.value = UiState(isLoading = isLoading, cause = cause, value = value)
    }
}

