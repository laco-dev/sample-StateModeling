package com.laco.sample.state.ui.binding

import android.view.View
import androidx.core.view.isVisible
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

@Suppress("UNCHECKED_CAST")
@BindingAdapter("items")
fun <T> RecyclerView.setItems(items: List<T>?) {
    val adapter = adapter as? ListAdapter<T, *>
    adapter?.submitList(items)
}

@BindingAdapter("android:visibility")
fun View.setVisible(isVisible: Boolean) {
    this.isVisible = isVisible
}
