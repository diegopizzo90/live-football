package com.diegopizzo.livefootball.match.domain.config

import com.diegopizzo.livefootball.match.domain.usecase.GetMatchesByDateUseCase
import com.diegopizzo.livefootball.match.domain.usecase.GetMatchesByDateUseCaseImpl
import org.koin.dsl.module

private val getMatchesByDateUseCaseModule = module {
    factory<GetMatchesByDateUseCase> {
        GetMatchesByDateUseCaseImpl(
            matchRepository = get(),
            refreshIntervalMs = 60000, // 1 minute,
        )
    }
}

val matchDomainModule = module {
    includes(getMatchesByDateUseCaseModule)
}
