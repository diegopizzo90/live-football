package com.diegopizzo.match.presentation.config

import com.diegopizzo.match.presentation.mapper.MatchViewDataMapper
import com.diegopizzo.match.presentation.mapper.MatchViewDataMapperImpl
import com.diegopizzo.match.presentation.usecase.GetMatchesByDateUseCase
import com.diegopizzo.match.presentation.usecase.GetMatchesByDateUseCaseImpl
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
            matchViewDataMapper = get(),
            refreshIntervalMs = 120000L, // 120 seconds,
        )
    }
}

val matchPresentationModule = module {
    includes(
        matchViewDataMapperModule,
        getMatchesByDateUseCaseModule,
    )
}
