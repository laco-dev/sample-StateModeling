package com.laco.sample.state.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.mapLatest
import kotlinx.coroutines.flow.stateIn

interface UiState

abstract class BaseViewModel<T : UiState>(initialState: T) : ViewModel() {

    private val _state = MutableStateFlow(initialState)
    val state = _state.asStateFlow()

    protected fun updateState(transform: (T) -> T) {
        val value = state.value
        _state.value = transform(value)
    }

    protected fun <R> StateFlow<T>.mapState(transform: (value: T) -> R): StateFlow<R> =
        mapLatest(transform)
            .stateIn(viewModelScope, SharingStarted.Eagerly, transform(value))

    protected fun withState(action: (T) -> Unit) = action(state.value)
}
