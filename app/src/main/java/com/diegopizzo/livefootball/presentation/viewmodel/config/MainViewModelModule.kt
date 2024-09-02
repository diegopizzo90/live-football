package com.diegopizzo.livefootball.presentation.viewmodel.config

import com.diegopizzo.livefootball.presentation.viewmodel.MainViewModel
import kotlinx.coroutines.Dispatchers
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val mainViewModelModule = module {
    viewModel {
        MainViewModel(get(), get(), Dispatchers.IO)
    }
}
