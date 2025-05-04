package com.diegopizzo.livefootball.match.api.config

import com.diegopizzo.livefootball.core.utils.SqlDriverFactory
import com.diegopizzo.livefootball.core.utils.getSeasonYear
import com.diegopizzo.livefootball.match.api.network.MatchApi
import com.diegopizzo.livefootball.match.api.network.MatchApiImpl
import com.diegopizzo.livefootball.match.api.repository.MatchRepositoryImpl
import com.diegopizzo.livefootball.match.api.repository.store.MatchDbRepository
import com.diegopizzo.livefootball.match.api.repository.store.MatchDbRepositoryImpl
import com.diegopizzo.livefootball.match.api.repository.store.MatchStore
import com.diegopizzo.livefootball.match.api.repository.store.MatchStoreImpl
import com.diegopizzo.livefootball.match.domain.repository.MatchRepository
import org.koin.dsl.module
import sqldelight.database.MatchDatabase
import kotlin.time.Duration.Companion.seconds

private val matchNetworkModule = module {
    single<MatchApi> {
        MatchApiImpl(get())
    }
}

private val matchMapperModule = module {
    factory<com.diegopizzo.livefootball.match.api.repository.store.mapper.MatchMapper> {
        com.diegopizzo.livefootball.match.api.repository.store.mapper.MatchMapperImpl()
    }
}

private val matchStoreModule = module {
    single<MatchStore> {
        MatchStoreImpl(get(), get(), get(), get(), ttlCacheInSeconds = 30.seconds)
    }
}

private val matchRepositoryModule = module {
    factory<MatchRepository> {
        MatchRepositoryImpl(get(), getSeasonYear())
    }
}

private fun matchDatabaseModule(driverFactory: SqlDriverFactory) =
    module {
        single {
            val schema = MatchDatabase.Schema
            val dbName = "lf-match-db"
            val driver = driverFactory.createDriver(schema, dbName)
            MatchDatabase(driver)
        }
    }

private val matchDatabaseRepositoryModule = module {
    single<MatchDbRepository> {
        MatchDbRepositoryImpl(get<MatchDatabase>().matchQueries)
    }
}

fun matchApiModule(driverFactory: SqlDriverFactory) = module {
    includes(
        matchNetworkModule,
        matchMapperModule,
        matchStoreModule,
        matchRepositoryModule,
        matchDatabaseModule(driverFactory),
        matchDatabaseRepositoryModule,
    )
}
