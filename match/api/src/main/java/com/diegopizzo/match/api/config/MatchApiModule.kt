package com.diegopizzo.match.api.config

import androidx.room.Room
import com.diegopizzo.core.utils.getSeasonYear
import com.diegopizzo.match.api.network.MatchApi
import com.diegopizzo.match.api.network.MatchApiImpl
import com.diegopizzo.match.api.repository.MatchRepository
import com.diegopizzo.match.api.repository.MatchRepositoryImpl
import com.diegopizzo.match.api.repository.store.MatchStore
import com.diegopizzo.match.api.repository.store.MatchStoreImpl
import com.diegopizzo.match.api.repository.store.dao.MatchDao
import com.diegopizzo.match.api.repository.store.dao.MatchDbRepository
import com.diegopizzo.match.api.repository.store.dao.MatchDbRepositoryImpl
import com.diegopizzo.match.api.repository.store.database.MatchDatabase
import com.diegopizzo.match.api.repository.store.mapper.MatchMapper
import com.diegopizzo.match.api.repository.store.mapper.MatchMapperImpl
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module
import kotlin.time.Duration.Companion.seconds

private val matchNetworkModule = module {
    single<MatchApi> {
        MatchApiImpl(get())
    }
}

private val matchMapperModule = module {
    factory<MatchMapper> {
        MatchMapperImpl()
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
        Room.databaseBuilder(androidApplication(), MatchDatabase::class.java, "lf-match-db").build()
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
