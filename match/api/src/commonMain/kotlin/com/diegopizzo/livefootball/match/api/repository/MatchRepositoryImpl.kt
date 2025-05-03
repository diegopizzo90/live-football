package com.diegopizzo.livefootball.match.api.repository

import com.diegopizzo.livefootball.match.api.repository.store.MatchStore
import com.diegopizzo.livefootball.match.domain.repository.MatchRepository
import com.diegopizzo.livefootball.match.domain.repository.model.MatchData

internal class MatchRepositoryImpl(
    private val store: MatchStore,
    private val season: String,
) : MatchRepository {
    override suspend fun getMatches(
        date: String,
        leagueIds: List<Long>,
    ): Result<List<MatchData>> {
        return store.getMatches(
            date = date,
            season = season,
            leagueIds = leagueIds,
        )
    }
}
