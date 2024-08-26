package com.diegopizzo.match.api.config

import com.diegopizzo.match.api.network.MatchApi
import com.diegopizzo.match.api.network.MatchApiImpl
import com.diegopizzo.match.api.repository.store.MatchStore
import com.diegopizzo.match.api.repository.store.MatchStoreImpl
import com.diegopizzo.match.api.repository.store.mapper.MatchDataMapper
import com.diegopizzo.match.api.repository.store.mapper.MatchDataMapperImpl
import org.koin.dsl.module

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
        MatchStoreImpl(get(), get(), ttlCacheInMinutes = 1)
    }
}

val matchApiModule = module {
    includes(
        matchNetworkModule,
        matchDataMapperModule,
        matchStoreModule,
    )
}
