package com.diegopizzo.livefootball.match.presentation.config

import com.diegopizzo.livefootball.match.presentation.viewmodel.AndroidMatchViewModel
import kotlinx.coroutines.Dispatchers
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

private val androidMatchViewModel = module {
    viewModel {
        AndroidMatchViewModel(get(), Dispatchers.IO, get(), get())
    }
}

val matchPresentationAndroidModule = module {
    includes(
        androidMatchViewModel,
    )
}
