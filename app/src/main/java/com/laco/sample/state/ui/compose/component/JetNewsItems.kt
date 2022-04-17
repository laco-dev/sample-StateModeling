package com.laco.sample.state.ui.compose.component

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.laco.sample.state.ui.model.NewsItemUiState

@Composable
fun JetNewsItems(modifier: Modifier = Modifier, items: List<NewsItemUiState>, onBookmark: (NewsItemUiState) -> Unit) {
    LazyColumn(modifier = modifier) {
        items(items = items, key = { it.id }) { item ->
            JetNewsItem(
                item = item,
                onBookmark = { onBookmark(it) }
            )
        }
    }
}
