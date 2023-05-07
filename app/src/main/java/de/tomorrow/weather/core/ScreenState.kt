package de.tomorrow.weather.core

sealed class ScreenState<out T> {
    object Loading : ScreenState<Nothing>()
    data class Content<T>(val data: T) : ScreenState<T>()
    data class Error(val exception: Throwable) : ScreenState<Nothing>()
}