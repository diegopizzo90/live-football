package com.diegopizzo.match.api.repository.store

import com.diegopizzo.match.api.network.MatchApi
import com.diegopizzo.match.api.repository.store.mapper.MatchDataMapper
import com.diegopizzo.match.api.repository.store.model.MatchData
import org.mobilenativefoundation.store.store5.Fetcher
import org.mobilenativefoundation.store.store5.MemoryPolicy
import org.mobilenativefoundation.store.store5.Store
import org.mobilenativefoundation.store.store5.StoreBuilder
import org.mobilenativefoundation.store.store5.impl.extensions.fresh
import org.mobilenativefoundation.store.store5.impl.extensions.get
import kotlin.time.Duration.Companion.milliseconds

internal interface MatchStore {
    suspend fun getMatches(
        leagueId: Long,
        from: String,
        to: String,
        season: String,
        forceRefresh: Boolean = false,
    ): Result<List<MatchData>>
}

internal class MatchStoreImpl(
    api: MatchApi,
    private val mapper: MatchDataMapper,
    ttlCacheInMilliseconds: Int,
) : MatchStore {

    private val cachePolicy = MemoryPolicy.builder<Any, Any>()
        .setExpireAfterWrite(ttlCacheInMilliseconds.milliseconds)
        .build()

    private val store: Store<MatchStoreKey, Result<List<MatchData>>> = StoreBuilder.from(
        fetcher = provideFetcher(api),
    ).cachePolicy(cachePolicy).build()

    private fun provideFetcher(
        api: MatchApi,
    ): Fetcher<MatchStoreKey, Result<List<MatchData>>> = Fetcher.of { key: MatchStoreKey ->
        api.getMatches(
            leagueId = key.leagueId,
            from = key.from,
            to = key.to,
            season = key.season,
        ).mapCatching {
            mapper.mapToMatchData(it)
        }
    }

    override suspend fun getMatches(
        leagueId: Long,
        from: String,
        to: String,
        season: String,
        forceRefresh: Boolean,
    ): Result<List<MatchData>> {
        val key = MatchStoreKey(
            leagueId = leagueId,
            from = from,
            to = to,
            season = season,
        )

        return if (forceRefresh) store.fresh(key) else store.get(key)
    }
}

internal data class MatchStoreKey(
    val leagueId: Long,
    val from: String,
    val to: String,
    val season: String,
)
