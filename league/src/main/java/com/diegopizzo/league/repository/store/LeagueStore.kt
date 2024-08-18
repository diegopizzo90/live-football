package com.diegopizzo.league.repository.store

import com.diegopizzo.league.api.LeagueApi
import com.diegopizzo.league.config.LeaguesAvailable
import com.diegopizzo.league.repository.mapper.LeagueDataMapper
import com.diegopizzo.league.repository.model.LeagueData
import com.diegopizzo.league.repository.store.dao.LeagueDao
import com.diegopizzo.league.repository.store.entity.LeagueEntity
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import org.mobilenativefoundation.store.core5.ExperimentalStoreApi
import org.mobilenativefoundation.store.store5.Fetcher
import org.mobilenativefoundation.store.store5.MemoryPolicy
import org.mobilenativefoundation.store.store5.SourceOfTruth
import org.mobilenativefoundation.store.store5.Store
import org.mobilenativefoundation.store.store5.StoreBuilder
import org.mobilenativefoundation.store.store5.impl.extensions.get
import kotlin.time.Duration.Companion.minutes

internal interface LeagueStore {
    suspend fun getLeague(leagueAvailable: LeaguesAvailable): Result<LeagueData>
    suspend fun delete()
}

internal class LeagueStoreImpl(
    api: LeagueApi,
    dao: LeagueDao,
    private val mapper: LeagueDataMapper,
    ttlCacheInMinutes: Int,
) : LeagueStore {

    private val store: Store<LeaguesAvailable, Result<LeagueData>> = StoreBuilder.from(
        fetcher = provideFetcher(api),
        sourceOfTruth = provideSourceOfTruth(dao),
    ).cachePolicy(
        MemoryPolicy.builder<Any, Any>()
            .setExpireAfterWrite(ttlCacheInMinutes.minutes)
            .build(),
    ).build()

    private fun provideFetcher(
        api: LeagueApi,
    ): Fetcher<LeaguesAvailable, LeagueEntity> = Fetcher.of { key: LeaguesAvailable ->
        api.getLeagueInfo(
            league = key,
        ).mapCatching {
            mapper.mapLeagueEntity(it.response.first())
        }.getOrThrow()
    }

    private fun provideSourceOfTruth(
        dao: LeagueDao,
    ): SourceOfTruth<LeaguesAvailable, LeagueEntity, Result<LeagueData>> = SourceOfTruth.of(
        reader = { key: LeaguesAvailable ->
            flow {
                emit(dao.getLeagueByName(key.leagueName))
            }.map {
                it?.let {
                    try {
                        Result.success(mapper.mapLeagueData(it))
                    } catch (e: Exception) {
                        Result.failure(e)
                    }
                }
            }
        },
        writer = { _: LeaguesAvailable, input: LeagueEntity ->
            dao.insertLeague(input)
        },
        delete = { key: LeaguesAvailable ->
            dao.deleteByName(key.leagueName)
        },
        deleteAll = { dao.deleteAll() },
    )

    override suspend fun getLeague(leagueAvailable: LeaguesAvailable): Result<LeagueData> {
        return store.get(leagueAvailable)
    }

    @OptIn(ExperimentalStoreApi::class)
    override suspend fun delete() {
        return store.clear()
    }
}
