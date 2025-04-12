package com.diegopizzo.livefootball.league.domain.config

import com.diegopizzo.livefootball.league.domain.usecase.GetLeagueIdsUseCase
import com.diegopizzo.livefootball.league.domain.usecase.GetLeagueIdsUseCaseImpl
import com.diegopizzo.livefootball.league.domain.usecase.GetLeaguesUseCase
import com.diegopizzo.livefootball.league.domain.usecase.GetLeaguesUseCaseImpl
import org.koin.dsl.module

private val getLeaguesUseCaseModule = module {
    factory<GetLeaguesUseCase> { GetLeaguesUseCaseImpl(get()) }
}

private val getLeagueIdsUseCaseModule = module {
    factory<GetLeagueIdsUseCase> { GetLeagueIdsUseCaseImpl(get()) }
}

val leagueDomainModule = module {
    includes(
        getLeaguesUseCaseModule,
        getLeagueIdsUseCaseModule,
    )
}
