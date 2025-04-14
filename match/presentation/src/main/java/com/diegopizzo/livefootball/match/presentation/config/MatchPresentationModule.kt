package com.diegopizzo.livefootball.match.presentation.config

import com.diegopizzo.livefootball.match.presentation.mapper.MatchViewDataMapper
import com.diegopizzo.livefootball.match.presentation.mapper.MatchViewDataMapperImpl
import com.diegopizzo.livefootball.match.presentation.viewmodel.AndroidMatchViewModel
import com.diegopizzo.livefootball.match.presentation.viewmodel.MatchCoordinator
import com.diegopizzo.livefootball.match.presentation.viewmodel.MatchCoordinatorImpl
import kotlinx.coroutines.Dispatchers
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

private val matchViewDataMapperModule = module {
    factory<MatchViewDataMapper> {
        MatchViewDataMapperImpl(get())
    }
}

private val matchCoordinator = module {
    factory<MatchCoordinator> {
        MatchCoordinatorImpl(get(), get())
    }
}

private val androidMatchViewModel = module {
    viewModel {
        AndroidMatchViewModel(get(), Dispatchers.IO, get(), get())
    }
}

val matchPresentationModule = module {
    includes(
        matchViewDataMapperModule,
        matchCoordinator,
        androidMatchViewModel,
    )
}
