package com.diegopizzo.match.api.config

import com.diegopizzo.core.utils.getSeasonYear
import com.diegopizzo.match.api.network.MatchApi
import com.diegopizzo.match.api.network.MatchApiImpl
import com.diegopizzo.match.api.repository.MatchRepository
import com.diegopizzo.match.api.repository.MatchRepositoryImpl
import com.diegopizzo.match.api.repository.store.MatchStore
import com.diegopizzo.match.api.repository.store.MatchStoreImpl
import com.diegopizzo.match.api.repository.store.mapper.MatchDataMapper
import com.diegopizzo.match.api.repository.store.mapper.MatchDataMapperImpl
import org.koin.dsl.module
import kotlin.time.Duration.Companion.seconds

private val matchNetworkModule = module {
    single<MatchApi> {
        MatchApiImpl(get())
    }
}

private val matchDataMapperModule = module {
    factory<MatchDataMapper> {
        MatchDataMapperImpl()
    }
}

private val matchStoreModule = module {
    single<MatchStore> {
        MatchStoreImpl(get(), get(), ttlCacheInSeconds = 30.seconds)
    }
}

private val matchRepositoryModule = module {
    factory<MatchRepository> {
        MatchRepositoryImpl(get(), getSeasonYear())
    }
}

val matchApiModule = module {
    includes(
        matchNetworkModule,
        matchDataMapperModule,
        matchStoreModule,
        matchRepositoryModule,
    )
}
