package com.laco.sample.state.ui.sample1

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.laco.sample.state.R
import com.laco.sample.state.databinding.ActivityMain1Binding
import com.laco.sample.state.ui.adapter.NewsAdapter

class MainActivity : AppCompatActivity() {

    private val adapter: NewsAdapter by lazy {
        NewsAdapter(
            onBookmark = { viewModel.toggleBookmark(it) }
        )
    }

    private val viewModel: MainViewModel by viewModels { MainViewModelFactory() }
    private lateinit var binding: ActivityMain1Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main_1)
        binding.lifecycleOwner = this
        binding.vm = viewModel

        binding.recyclerView.adapter = adapter
        binding.recyclerView.itemAnimator = null

        viewModel.fetchNewsItems()
    }
}
