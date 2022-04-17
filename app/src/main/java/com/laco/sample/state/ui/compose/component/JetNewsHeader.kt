package com.laco.sample.state.ui.compose.component

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp

@Composable
fun JetNewsHeader(modifier: Modifier = Modifier) {
    Text(
        modifier = modifier,
        text = "Jetnews",
        style = MaterialTheme.typography.h6.copy(fontStyle = FontStyle.Italic)
    )
    Spacer(modifier = Modifier.height(8.dp))
    Divider(color = Color.Black.copy(alpha = 0.25f))
}
