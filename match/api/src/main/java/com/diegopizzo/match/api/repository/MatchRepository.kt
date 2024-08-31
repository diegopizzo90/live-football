package com.diegopizzo.match.api.repository

import com.diegopizzo.match.api.repository.store.MatchStore
import com.diegopizzo.match.api.repository.store.model.MatchData

interface MatchRepository {
    suspend fun getMatches(
        leagueId: Long,
        from: String,
        to: String,
        forceRefresh: Boolean = false,
    ): Result<List<MatchData>>
}

internal class MatchRepositoryImpl(
    private val store: MatchStore,
    private val season: String,
) : MatchRepository {
    override suspend fun getMatches(
        leagueId: Long,
        from: String,
        to: String,
        forceRefresh: Boolean,
    ): Result<List<MatchData>> {
        return store.getMatches(
            leagueId = leagueId,
            from = from,
            to = to,
            season = season,
            forceRefresh = forceRefresh,
        )
    }
}
