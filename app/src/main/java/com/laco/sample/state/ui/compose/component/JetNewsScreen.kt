package com.laco.sample.state.ui.compose.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.laco.sample.state.ui.model.NewsItemUiState

@Composable
fun JetNewsScreen(
    newsItems: List<NewsItemUiState>,
    isEmpty: Boolean,
    isLoading: Boolean,
    isError: Boolean,
    onBookmark: (NewsItemUiState) -> Unit
) {

    Box(modifier = Modifier.fillMaxSize()) {
        JetNewsContent(
            items = newsItems,
            onBookmark = { onBookmark(it) }
        )

        if (isEmpty) {
            JetNewsEmpty(modifier = Modifier.align(Alignment.Center))
        }

        if (isLoading) {
            JetNewsLoading(modifier = Modifier.align(Alignment.Center))
        }

        if (isError) {
            JetNewsError(modifier = Modifier.align(Alignment.BottomCenter))
        }
    }
}
