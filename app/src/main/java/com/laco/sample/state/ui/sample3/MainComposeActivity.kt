package com.laco.sample.state.ui.sample3

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.laco.sample.state.ui.compose.component.JetNewsScreen

class MainComposeActivity : ComponentActivity() {

    private val viewModel: MainViewModel by viewModels { MainViewModelFactory() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MaterialTheme {
                MainScreen(viewModel)
            }
        }

        viewModel.fetchNewsItems()
    }
}

@Composable
fun MainScreen(viewModel: MainViewModel) {
    val state by viewModel.state.collectAsState()

    JetNewsScreen(
        newsItems = state.newsItems,
        isEmpty = state.isEmpty,
        isLoading = state.isLoading,
        isError = state.isError,
        onBookmark = { viewModel.toggleBookmark(it) }
    )
}


