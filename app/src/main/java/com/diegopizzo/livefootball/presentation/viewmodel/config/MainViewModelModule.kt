package com.diegopizzo.livefootball.presentation.viewmodel.config

import com.diegopizzo.livefootball.presentation.viewmodel.AndroidMainViewModel
import kotlinx.coroutines.Dispatchers
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val androidMainViewModelModule = module {
    viewModel {
        AndroidMainViewModel(get(), get(), Dispatchers.IO)
    }
}
