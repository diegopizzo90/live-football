package com.diegopizzo.livefootball.league.api.config

import androidx.room.Room
import com.diegopizzo.livefootball.league.api.network.LeagueApi
import com.diegopizzo.livefootball.league.api.network.LeagueApiImpl
import com.diegopizzo.livefootball.league.api.repository.LeagueRepositoryImpl
import com.diegopizzo.livefootball.league.api.repository.mapper.LeagueDataMapper
import com.diegopizzo.livefootball.league.api.repository.store.LeagueStore
import com.diegopizzo.livefootball.league.api.repository.store.LeagueStoreImpl
import com.diegopizzo.livefootball.league.api.repository.store.dao.LeagueDao
import com.diegopizzo.livefootball.league.api.repository.store.database.LeagueDatabase
import com.diegopizzo.livefootball.league.domain.repository.LeagueRepository
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

private val leagueApiModule = module {
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

private val leagueDatabaseModule = module {
    single {
        Room.databaseBuilder(androidApplication(), LeagueDatabase::class.java, "lf-league-db").build()
    }
}

private val leagueDaoModule = module {
    single {
        provideLeagueDao(get())
    }
}

private fun provideLeagueDao(database: LeagueDatabase): LeagueDao {
    return database.leagueDao()
}

private val leagueStoreModule = module {
    single<LeagueStore> {
        LeagueStoreImpl(get(), get(), get(), ttlCacheInMinutes = 10)
    }
}

val leagueModule = module {
    includes(
        leagueApiModule,
        leagueRepositoryModule,
        leagueMapperModule,
        leagueDatabaseModule,
        leagueDaoModule,
        leagueStoreModule,
    )
}
