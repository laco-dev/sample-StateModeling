package com.laco.sample.state.ui.sample1

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.laco.sample.state.domain.usecase.GetNewsItemListUseCase

class MainViewModelFactory : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T = MainViewModel(
        GetNewsItemListUseCase()
    ) as T
}
