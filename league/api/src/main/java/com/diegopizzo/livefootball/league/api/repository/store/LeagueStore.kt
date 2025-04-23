package com.diegopizzo.livefootball.league.api.repository.store

import com.diegopizzo.livefootball.league.api.config.LeaguesAvailable
import com.diegopizzo.livefootball.league.api.network.LeagueApi
import com.diegopizzo.livefootball.league.api.repository.mapper.LeagueDataMapper
import com.diegopizzo.livefootball.league.api.repository.store.dao.LeagueDbRepository
import com.diegopizzo.livefootball.league.domain.repository.model.LeagueData
import database.LeagueEntity
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
    dbRepository: LeagueDbRepository,
    private val mapper: LeagueDataMapper,
    ttlCacheInMinutes: Int,
) : LeagueStore {

    private val store: Store<LeaguesAvailable, Result<LeagueData>> = StoreBuilder.from(
        fetcher = provideFetcher(api),
        sourceOfTruth = provideSourceOfTruth(dbRepository),
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
        dbRepository: LeagueDbRepository,
    ): SourceOfTruth<LeaguesAvailable, LeagueEntity, Result<LeagueData>> = SourceOfTruth.of(
        reader = { key: LeaguesAvailable ->
            flow {
                emit(dbRepository.getLeagueByName(key.leagueName))
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
            dbRepository.insertLeague(input)
        },
        delete = { key: LeaguesAvailable ->
            dbRepository.deleteByName(key.leagueName)
        },
        deleteAll = { dbRepository.deleteAll() },
    )

    override suspend fun getLeague(leagueAvailable: LeaguesAvailable): Result<LeagueData> {
        return store.get(leagueAvailable)
    }

    @OptIn(ExperimentalStoreApi::class)
    override suspend fun delete() {
        return store.clear()
    }
}
