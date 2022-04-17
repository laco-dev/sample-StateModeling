package com.laco.sample.state.ui.compose.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.laco.sample.state.ui.model.NewsItemUiState

@Composable
fun JetNewsContent(
    items: List<NewsItemUiState>,
    onBookmark: (NewsItemUiState) -> Unit
) {
    JetNewsContainer {
        JetNewsItems(
            items = items,
            onBookmark = onBookmark,
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
        )
    }
}

@Composable
fun JetNewsContainer(
    modifier: Modifier = Modifier,
    content: @Composable ColumnScope.() -> Unit
) {
    Column(modifier.padding(20.dp)) {
        JetNewsHeader(modifier = Modifier.align(Alignment.CenterHorizontally))
        content()
    }
}
