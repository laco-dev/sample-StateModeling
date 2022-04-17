package com.laco.sample.state.ui.compose.component

import androidx.compose.runtime.Composable
import com.laco.sample.state.ui.sample4.UiState

@Composable
fun <T> UiStateComposable(
    state: UiState<T>,
    onSuccess: @Composable (T) -> Unit,
    onLoading: @Composable () -> Unit = { JetNewsLoading() },
    onError: @Composable () -> Unit = { JetNewsError() }
) {
    when {
        state.isLoading -> onLoading()
        state.isError -> onError()
        state.value != null -> onSuccess(state.value)
    }
}
