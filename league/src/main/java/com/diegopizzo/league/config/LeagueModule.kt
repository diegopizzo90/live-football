package com.diegopizzo.league.config

import androidx.room.Room
import com.diegopizzo.league.api.LeagueApi
import com.diegopizzo.league.api.LeagueApiImpl
import com.diegopizzo.league.repository.LeagueRepository
import com.diegopizzo.league.repository.LeagueRepositoryImpl
import com.diegopizzo.league.repository.mapper.LeagueDataMapper
import com.diegopizzo.league.repository.store.LeagueStore
import com.diegopizzo.league.repository.store.LeagueStoreImpl
import com.diegopizzo.league.repository.store.config.AppDatabase
import com.diegopizzo.league.repository.store.dao.LeagueDao
import kotlinx.coroutines.Dispatchers
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

private val leagueApiModule = module {
    single<LeagueApi> {
        LeagueApiImpl(get())
    }
}

private val leagueRepositoryModule = module {
    factory<LeagueRepository> {
        LeagueRepositoryImpl(get(), Dispatchers.IO)
    }
}

private val leagueMapperModule = module {
    factory {
        LeagueDataMapper()
    }
}

private val leagueDatabaseModule = module {
    single {
        Room.databaseBuilder(androidApplication(), AppDatabase::class.java, "lf-league-db").build()
    }
}

private val leagueDaoModule = module {
    single {
        provideLeagueDao(get())
    }
}

private fun provideLeagueDao(database: AppDatabase): LeagueDao {
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
