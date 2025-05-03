package com.diegopizzo.livefootball.match.presentation.config

import com.diegopizzo.livefootball.match.presentation.mapper.MatchViewDataMapper
import com.diegopizzo.livefootball.match.presentation.mapper.MatchViewDataMapperImpl
import com.diegopizzo.livefootball.match.presentation.viewmodel.MatchCoordinator
import com.diegopizzo.livefootball.match.presentation.viewmodel.MatchCoordinatorImpl
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

val matchPresentationCommonModule = module {
    includes(
        matchViewDataMapperModule,
        matchCoordinator,
    )
}
