package com.diegopizzo.livefootball.league.api.config

import com.diegopizzo.livefootball.core.utils.SqlDriverFactory
import com.diegopizzo.livefootball.league.api.network.LeagueApi
import com.diegopizzo.livefootball.league.api.network.LeagueApiImpl
import com.diegopizzo.livefootball.league.api.repository.LeagueRepositoryImpl
import com.diegopizzo.livefootball.league.api.repository.mapper.LeagueDataMapper
import com.diegopizzo.livefootball.league.api.repository.store.LeagueDbRepository
import com.diegopizzo.livefootball.league.api.repository.store.LeagueDbRepositoryImpl
import com.diegopizzo.livefootball.league.api.repository.store.LeagueStore
import com.diegopizzo.livefootball.league.api.repository.store.LeagueStoreImpl
import com.diegopizzo.livefootball.league.domain.repository.LeagueRepository
import org.koin.dsl.module
import sqldelight.database.LeagueDatabase

private val apiModule = module {
    single<LeagueApi> {
        LeagueApiImpl(get())
    }
}

private val leagueRepositoryModule = module {
    factory<LeagueRepository> {
        LeagueRepositoryImpl(get())
    }
}

private val leagueMapperModule = module {
    factory {
        LeagueDataMapper()
    }
}

private fun leagueDatabaseModule(driverFactory: SqlDriverFactory) =
    module {
        single {
            val schema = LeagueDatabase.Schema
            val dbName = "lf-league-db"
            val driver = driverFactory.createDriver(schema, dbName)
            LeagueDatabase(driver)
        }
    }

private val leagueDatabaseRepositoryModule = module {
    single<LeagueDbRepository> {
        LeagueDbRepositoryImpl(get<LeagueDatabase>().leagueQueries)
    }
}

private val leagueStoreModule = module {
    single<LeagueStore> {
        LeagueStoreImpl(get(), get(), get(), ttlCacheInMinutes = 10)
    }
}

fun leagueApiModule(driverFactory: SqlDriverFactory) = module {
    includes(
        apiModule,
        leagueRepositoryModule,
        leagueMapperModule,
        leagueDatabaseModule(driverFactory),
        leagueDatabaseRepositoryModule,
        leagueStoreModule,
    )
}
