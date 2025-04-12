package com.diegopizzo.livefootball.match.api.config

import androidx.room.Room
import com.diegopizzo.livefootball.core.utils.getSeasonYear
import com.diegopizzo.livefootball.match.api.network.MatchApi
import com.diegopizzo.livefootball.match.api.network.MatchApiImpl
import com.diegopizzo.livefootball.match.api.repository.MatchRepositoryImpl
import com.diegopizzo.livefootball.match.api.repository.store.MatchStore
import com.diegopizzo.livefootball.match.api.repository.store.MatchStoreImpl
import com.diegopizzo.livefootball.match.api.repository.store.dao.MatchDao
import com.diegopizzo.livefootball.match.api.repository.store.dao.MatchDbRepository
import com.diegopizzo.livefootball.match.api.repository.store.dao.MatchDbRepositoryImpl
import com.diegopizzo.livefootball.match.api.repository.store.database.MatchDatabase
import com.diegopizzo.livefootball.match.api.repository.store.database.migration1_2
import com.diegopizzo.livefootball.match.domain.repository.MatchRepository
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module
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

private val matchDatabaseModule = module {
    single {
        Room.databaseBuilder(androidApplication(), MatchDatabase::class.java, "lf-match-db")
            .addMigrations(migration1_2)
            .build()
    }
}

private val matchDaoModule = module {
    single {
        provideMatchDao(get())
    }
}

private val matchDbRepositoryModule = module {
    single<MatchDbRepository> {
        MatchDbRepositoryImpl(get())
    }
}

private fun provideMatchDao(database: MatchDatabase): MatchDao {
    return database.matchDao()
}

val matchApiModule = module {
    includes(
        matchNetworkModule,
        matchMapperModule,
        matchStoreModule,
        matchRepositoryModule,
        matchDatabaseModule,
        matchDaoModule,
        matchDbRepositoryModule,
    )
}
