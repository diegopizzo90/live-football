package com.diegopizzo.core.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.plus

interface DispatcherProvider {
    val defaultDispatcher: CoroutineDispatcher
    val mainDispatcher: CoroutineDispatcher
        get() = Dispatchers.Main

    val ViewModel.backgroundScope: CoroutineScope
        get() = viewModelScope + defaultDispatcher
}
