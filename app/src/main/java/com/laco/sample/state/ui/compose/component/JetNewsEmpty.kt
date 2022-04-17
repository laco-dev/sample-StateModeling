package com.laco.sample.state.ui.compose.component

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun JetNewsEmpty(modifier: Modifier = Modifier) {
    Text(
        text = "데이터가 없습니다",
        style = MaterialTheme.typography.body2,
        modifier = modifier
    )
}
