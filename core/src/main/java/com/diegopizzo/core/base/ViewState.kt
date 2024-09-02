package com.diegopizzo.core.base

sealed class ViewState<T> {
    data class Success<T>(val data: T) : ViewState<T>()
    data class Error<T>(val message: String? = null) : ViewState<T>()
    class Loading<T>(isLoading: Boolean = true) : ViewState<T>()
}
