package com.laco.sample.state.ui.compose.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.laco.sample.state.R
import com.laco.sample.state.ui.model.NewsItemUiState

@Composable
fun JetNewsItem(
    item: NewsItemUiState,
    onBookmark: (NewsItemUiState) -> Unit
) {
    Column {
        Row {
            Column(
                Modifier
                    .wrapContentHeight()
                    .weight(1f)
            ) {
                Text(
                    modifier = Modifier.padding(top = 20.dp),
                    text = item.title,
                    style = MaterialTheme.typography.subtitle1
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    modifier = Modifier.alpha(0.8f),
                    text = item.body,
                    style = MaterialTheme.typography.body2,
                )
                Spacer(modifier = Modifier.height(20.dp))
            }
            val icon = if (item.bookmarked) {
                R.drawable.ic_baseline_bookmark_24
            } else {
                R.drawable.ic_baseline_bookmark_border_24
            }
            Icon(
                modifier = Modifier
                    .padding(top = 12.dp, end = 12.dp)
                    .size(48.dp)
                    .clickable { onBookmark(item) }
                    .padding(12.dp),
                painter = painterResource(id = icon),
                contentDescription = null,
                tint = Color.Black.copy(alpha = 0.6f)
            )
        }
        Divider(color = Color.Black.copy(alpha = 0.25f))
    }
}
