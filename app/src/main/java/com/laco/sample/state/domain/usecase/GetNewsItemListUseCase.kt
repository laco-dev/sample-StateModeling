package com.laco.sample.state.domain.usecase

import com.laco.sample.state.domain.model.NewsItem
import kotlinx.coroutines.delay
import java.util.UUID

class GetNewsItemListUseCase {

    suspend operator fun invoke(): Result<List<NewsItem>> = runCatching {
        delay(1500L)
        listOf(
            NewsItem(
                id = UUID.randomUUID().toString(),
                title = "Collections and sequences in Kotiln",
                body = "Florina Munt...",
                bookmarked = false
            ),
            NewsItem(
                id = UUID.randomUUID().toString(),
                title = "Dagger in Kotlin: Gotchas and Optimizations",
                body = "Manual Vivo",
                bookmarked = true
            )
        )
    }
}
