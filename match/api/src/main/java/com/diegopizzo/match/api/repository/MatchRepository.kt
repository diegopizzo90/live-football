package com.diegopizzo.match.api.repository

import com.diegopizzo.match.api.repository.store.MatchStore
import com.diegopizzo.match.api.repository.store.model.MatchData

interface MatchRepository {
    suspend fun getMatches(
        date: String,
        forceRefresh: Boolean = false,
    ): Result<List<MatchData>>
}

internal class MatchRepositoryImpl(
    private val store: MatchStore,
    private val season: String,
) : MatchRepository {
    override suspend fun getMatches(
        date: String,
        forceRefresh: Boolean,
    ): Result<List<MatchData>> {
        return store.getMatches(
            date = date,
            season = season,
            forceRefresh = forceRefresh,
        )
    }
}
