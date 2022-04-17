package com.laco.sample.state.ui.compose.component

import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@Composable
fun JetNewsLoading(modifier: Modifier = Modifier) {
    CircularProgressIndicator(
        modifier = modifier,
        color = Color(0xFF00AA88)
    )
}
