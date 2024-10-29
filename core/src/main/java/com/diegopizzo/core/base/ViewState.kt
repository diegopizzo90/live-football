package com.diegopizzo.core.base

sealed class ViewState<out T> {
    data class Success<T>(val data: T) : ViewState<T>()
    data class Error(val message: String? = null) : ViewState<Nothing>()
    data class Loading(val isLoading: Boolean = true, val showShimmer: Boolean = false) : ViewState<Nothing>()
}
