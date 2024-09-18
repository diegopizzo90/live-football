package com.diegopizzo.match.presentation.config

import com.diegopizzo.match.presentation.mapper.MatchViewDataMapper
import com.diegopizzo.match.presentation.mapper.MatchViewDataMapperImpl
import com.diegopizzo.match.presentation.usecase.GetMatchesByDateUseCase
import com.diegopizzo.match.presentation.usecase.GetMatchesByDateUseCaseImpl
import com.diegopizzo.match.presentation.viewmodel.MatchViewModel
import kotlinx.coroutines.Dispatchers
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

private val matchViewDataMapperModule = module {
    factory<MatchViewDataMapper> {
        MatchViewDataMapperImpl()
    }
}

private val getMatchesByDateUseCaseModule = module {
    factory<GetMatchesByDateUseCase> {
        GetMatchesByDateUseCaseImpl(
            matchRepository = get(),
            leagueRepository = get(),
            refreshIntervalMs = 60000, // 1 minute,
        )
    }
}

private val matchViewModel = module {
    viewModel {
        MatchViewModel(get(), Dispatchers.IO, get())
    }
}

val matchPresentationModule = module {
    includes(
        matchViewDataMapperModule,
        getMatchesByDateUseCaseModule,
        matchViewModel,
    )
}
