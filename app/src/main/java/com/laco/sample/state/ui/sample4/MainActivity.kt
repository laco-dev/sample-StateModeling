package com.laco.sample.state.ui.sample4

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.laco.sample.state.R
import com.laco.sample.state.databinding.ActivityMain4Binding
import com.laco.sample.state.ui.adapter.NewsAdapter

class MainActivity : AppCompatActivity() {

    private val adapter: NewsAdapter by lazy {
        NewsAdapter(
            onBookmark = { viewModel.toggleBookmark(it) }
        )
    }

    private val viewModel: MainViewModel by viewModels { MainViewModelFactory() }
    private lateinit var binding: ActivityMain4Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main_4)
        binding.lifecycleOwner = this
        binding.vm = viewModel

        binding.recyclerView.adapter = adapter
        binding.recyclerView.itemAnimator = null

        viewModel.fetchNewsItems()
    }
}
