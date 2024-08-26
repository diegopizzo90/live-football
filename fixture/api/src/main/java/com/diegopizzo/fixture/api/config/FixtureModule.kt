package com.diegopizzo.fixture.api.config

import com.diegopizzo.fixture.api.network.FixtureApi
import com.diegopizzo.fixture.api.network.FixtureApiImpl
import com.diegopizzo.fixture.api.repository.store.FixtureStore
import com.diegopizzo.fixture.api.repository.store.FixtureStoreImpl
import com.diegopizzo.fixture.api.repository.store.mapper.FixtureDataMapper
import com.diegopizzo.fixture.api.repository.store.mapper.FixtureDataMapperImpl
import org.koin.dsl.module

private val fixtureNetworkModule = module {
    single<FixtureApi> {
        FixtureApiImpl(get())
    }
}

private val fixtureDataMapperModule = module {
    factory<FixtureDataMapper> {
        FixtureDataMapperImpl()
    }
}

private val fixtureStoreModule = module {
    single<FixtureStore> {
        FixtureStoreImpl(get(), get(), ttlCacheInMinutes = 1)
    }
}

val fixtureApiModule = module {
    includes(
        fixtureNetworkModule,
        fixtureDataMapperModule,
        fixtureStoreModule,
    )
}
