package com.laco.sample.state.ui.sample4

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.laco.sample.state.ui.compose.component.JetNewsItems
import com.laco.sample.state.ui.compose.component.JetNewsScreen
import com.laco.sample.state.ui.compose.component.UiStateComposable

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
    val newsItems by viewModel.newsItems.collectAsState()
    val isLoading = state.isLoading
    val isEmpty = !isLoading && newsItems.isEmpty()
    val isError = state.isError

    JetNewsScreen(
        newsItems = newsItems,
        isEmpty = isEmpty,
        isLoading = isLoading,
        isError = isError,
        onBookmark = { viewModel.toggleBookmark(it) }
    )

    UiStateComposable(
        state = state,
        onSuccess = {
            JetNewsItems(
                items = it,
                onBookmark = { }
            )
        }
    )
}


