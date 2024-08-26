package com.diegopizzo.fixture.api.repository.store

import com.diegopizzo.fixture.api.network.FixtureApi
import com.diegopizzo.fixture.api.repository.store.mapper.FixtureDataMapper
import com.diegopizzo.fixture.api.repository.store.model.FixtureData
import org.mobilenativefoundation.store.store5.Fetcher
import org.mobilenativefoundation.store.store5.MemoryPolicy
import org.mobilenativefoundation.store.store5.Store
import org.mobilenativefoundation.store.store5.StoreBuilder
import org.mobilenativefoundation.store.store5.impl.extensions.fresh
import org.mobilenativefoundation.store.store5.impl.extensions.get
import kotlin.time.Duration.Companion.minutes

internal interface FixtureStore {
    suspend fun getFixtures(
        leagueId: Long,
        from: String,
        to: String,
        season: String,
        forceRefresh: Boolean = false,
    ): Result<List<FixtureData>>
}

internal class FixtureStoreImpl(
    api: FixtureApi,
    private val mapper: FixtureDataMapper,
    ttlCacheInMinutes: Int,
) : FixtureStore {

    private val cachePolicy = MemoryPolicy.builder<Any, Any>()
        .setExpireAfterWrite(ttlCacheInMinutes.minutes)
        .build()

    private val store: Store<FixtureStoreKey, Result<List<FixtureData>>> = StoreBuilder.from(
        fetcher = provideFetcher(api),
    ).cachePolicy(cachePolicy).build()

    private fun provideFetcher(
        api: FixtureApi,
    ): Fetcher<FixtureStoreKey, Result<List<FixtureData>>> = Fetcher.of { key: FixtureStoreKey ->
        api.getFixtures(
            leagueId = key.leagueId,
            from = key.from,
            to = key.to,
            season = key.season,
        ).mapCatching {
            mapper.mapToFixturesData(it)
        }
    }

    override suspend fun getFixtures(
        leagueId: Long,
        from: String,
        to: String,
        season: String,
        forceRefresh: Boolean,
    ): Result<List<FixtureData>> {

        val key = FixtureStoreKey(
            leagueId = leagueId,
            from = from,
            to = to,
            season = season,
        )

        return if (forceRefresh) store.fresh(key) else store.get(key)
    }
}

internal data class FixtureStoreKey(
    val leagueId: Long,
    val from: String,
    val to: String,
    val season: String,
)
