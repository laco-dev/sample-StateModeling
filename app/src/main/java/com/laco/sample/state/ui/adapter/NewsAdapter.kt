package com.laco.sample.state.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.laco.sample.state.databinding.ItemNewsBinding
import com.laco.sample.state.ui.model.NewsItemUiState

class NewsAdapter(
    private val onBookmark: (NewsItemUiState) -> Unit
) : ListAdapter<NewsItemUiState, NewsAdapter.ViewHolder>(NewsItemUiState.DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemNewsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position), onBookmark)
    }

    class ViewHolder(
        private val binding: ItemNewsBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: NewsItemUiState, onBookmark: (NewsItemUiState) -> Unit) {
            binding.item = item
            binding.ivBookmark.setOnClickListener { onBookmark(item) }
            binding.executePendingBindings()
        }
    }
}
