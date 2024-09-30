package com.diegopizzo.match.api.repository.store

import com.diegopizzo.core.utils.DateUtils
import com.diegopizzo.match.api.network.MatchApi
import com.diegopizzo.match.api.repository.store.dao.MatchDbRepository
import com.diegopizzo.match.api.repository.store.entity.MatchesResponseEntity
import com.diegopizzo.match.api.repository.store.mapper.MatchMapper
import com.diegopizzo.match.api.repository.store.model.MatchData
import com.diegopizzo.match.api.repository.store.model.MatchStatus
import org.mobilenativefoundation.store.store5.Fetcher
import org.mobilenativefoundation.store.store5.MemoryPolicy
import org.mobilenativefoundation.store.store5.SourceOfTruth
import org.mobilenativefoundation.store.store5.StoreBuilder
import org.mobilenativefoundation.store.store5.impl.extensions.fresh
import org.mobilenativefoundation.store.store5.impl.extensions.get
import kotlin.time.Duration

internal interface MatchStore {
    suspend fun getMatches(
        date: String,
        season: String,
        leagueIds: List<Long> = emptyList(),
        forceRefresh: Boolean = false,
    ): Result<List<MatchData>>
}

internal class MatchStoreImpl(
    private val api: MatchApi,
    private val matchMapper: MatchMapper,
    private val matchDbRepository: MatchDbRepository,
    private val dateUtils: DateUtils,
    ttlCacheInSeconds: Duration,
) : MatchStore {

    private val store = StoreBuilder.from(
        fetcher = provideFetcher(),
        sourceOfTruth = provideSourceOfTruth(),
    ).cachePolicy(
        MemoryPolicy.builder<Any, Any>()
            .setExpireAfterAccess(ttlCacheInSeconds)
            .setMaxSize(50)
            .build(),
    ).build()

    private fun provideFetcher(): Fetcher<MatchKeyStore, MatchesResponseEntity> = Fetcher.of { key: MatchKeyStore ->
        api.getMatches(
            date = key.date,
            season = key.season,
        ).mapCatching { matchResponseDto ->
            matchMapper.mapToMatchData(matchResponseDto, key.date, key.season)
            val matchesResponseEntity = matchMapper.mapToMatchData(matchResponseDto, key.date, key.season)

            // Filter matches by league IDs if provided
            val filteredMatches = if (key.leagueIds.isNotEmpty()) {
                matchesResponseEntity.matches.filter { match ->
                    match.league.idLeague in key.leagueIds
                }
            } else {
                matchesResponseEntity.matches
            }

            matchesResponseEntity.copy(matches = filteredMatches)
        }.getOrThrow()
    }

    private fun provideSourceOfTruth(): SourceOfTruth<MatchKeyStore, MatchesResponseEntity, List<MatchData>> =
        SourceOfTruth.of(
            nonFlowReader = { key: MatchKeyStore ->
                val matchesResponseEntity = matchDbRepository.getMatchesResponseByDateAndSeason(key.date, key.season)
                if (matchesResponseEntity == null) {
                    null
                } else {
                    matchMapper.mapToMatchData(matchesResponseEntity.matches)
                }
            },
            writer = { key, matches ->
                matchDbRepository.insertMatchesWithResponse(
                    matchDate = key.date,
                    season = key.season,
                    matches = matches.matches,
                )
            },
            delete = { key: MatchKeyStore ->
                matchDbRepository.deleteByDateAndSeason(key.date, key.season)
            },
            deleteAll = { matchDbRepository.deleteAll() },
        )

    override suspend fun getMatches(
        date: String,
        season: String,
        leagueIds: List<Long>,
        forceRefresh: Boolean,
    ): Result<List<MatchData>> {
        val key = MatchKeyStore(
            date = date,
            season = season,
            leagueIds = leagueIds,
        )

        return runCatching {
            // If force refresh is required, return fresh data
            if (forceRefresh) {
                return Result.success(store.fresh(key))
            }

            // Retrieve match data
            val matchData = store.get(key)

            // Return fresh data if required, otherwise return existing cached data
            if (isFreshDataRequired(matchData, key.date)) {
                Result.success(store.fresh(key))
            } else {
                Result.success(matchData)
            }
        }.getOrElse {
            Result.failure(it)
        }
    }

    private fun isFreshDataRequired(matchData: List<MatchData>, date: String): Boolean {
        if (!dateUtils.isToday(date)) {
            return matchData.any { isMatchPlaying(it.status.matchStatus) }
        }

        if (matchData.all { isMatchFinished(it.status.matchStatus) }) {
            return false
        }

        val currentTimestamp = dateUtils.getCurrentUnixTimestamp()
        return matchData.any { currentTimestamp >= it.timestampUtc }
    }

    private fun isMatchFinished(status: MatchStatus?): Boolean {
        return status?.isLive == false
    }

    private fun isMatchPlaying(status: MatchStatus?): Boolean {
        return status?.isMatchPlaying == true
    }
}

internal data class MatchKeyStore(
    val date: String,
    val season: String,
    val leagueIds: List<Long>,
)
