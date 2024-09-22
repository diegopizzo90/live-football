package com.diegopizzo.match.api.repository.store

import com.diegopizzo.match.api.network.MatchApi
import com.diegopizzo.match.api.repository.store.mapper.MatchDataMapper
import com.diegopizzo.match.api.repository.store.model.MatchData
import org.mobilenativefoundation.store.store5.Fetcher
import org.mobilenativefoundation.store.store5.MemoryPolicy
import org.mobilenativefoundation.store.store5.StoreBuilder
import org.mobilenativefoundation.store.store5.impl.extensions.fresh
import org.mobilenativefoundation.store.store5.impl.extensions.get
import kotlin.time.Duration

internal interface MatchStore {
    suspend fun getMatches(
        date: String,
        season: String,
        forceRefresh: Boolean = false,
    ): Result<List<MatchData>>
}

internal class MatchStoreImpl(
    private val api: MatchApi,
    private val mapper: MatchDataMapper,
    ttlCacheInSeconds: Duration,
) : MatchStore {

    private val store = StoreBuilder.from(
        fetcher = provideFetcher(),
    ).cachePolicy(
        MemoryPolicy.builder<Any, Any>()
            .setExpireAfterAccess(ttlCacheInSeconds)
            .build(),
    ).build()

    private fun provideFetcher() = Fetcher.of { key: MatchStoreKey ->
        api.getMatches(
            date = key.date,
            season = key.season,
        ).mapCatching {
            mapper.mapToMatchData(it)
        }
    }

    override suspend fun getMatches(
        date: String,
        season: String,
        forceRefresh: Boolean,
    ): Result<List<MatchData>> {
        val key = MatchStoreKey(
            date = date,
            season = season,
        )
        return if (forceRefresh) store.fresh(key) else store.get(key)
    }
}

internal data class MatchStoreKey(
    val date: String,
    val season: String,
)
