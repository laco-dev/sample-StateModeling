package com.laco.sample.state.ui.sample4

sealed class UiState2<out T> {

    data class Success<T>(val value: T) : UiState2<T>()

    data class Loading<T>(val value: T? = null) : UiState2<T>()

    data class Failure(val cause: Throwable?) : UiState2<Nothing>()

    fun getOrDefault(defaultValue: @UnsafeVariance T): T {
        if (this is Success) {
            return value
        } else {
            return defaultValue
        }
    }

    companion object {
        fun <T> success(value: T): UiState2<T> = Success(value)

        fun <T> loading(): UiState2<T> = Loading(null)

        fun <T> failure(cause: Throwable): UiState2<T> = Failure(cause)
    }
}
