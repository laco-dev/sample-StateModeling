package com.laco.sample.state.ui.compose.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun JetNewsError(modifier: Modifier = Modifier) {
    Card(
        backgroundColor = Color(0xFFFF4444),
        modifier = modifier
            .padding(8.dp)
            .fillMaxWidth()
            .wrapContentHeight()
    ) {
        Text(
            modifier = Modifier.padding(8.dp),
            text = "오류가 발생하였습니다",
            color = Color.White,
            style = MaterialTheme.typography.body2.copy(fontWeight = FontWeight.Bold)
        )
    }

}
