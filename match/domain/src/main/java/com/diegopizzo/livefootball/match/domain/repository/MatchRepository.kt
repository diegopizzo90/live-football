package com.diegopizzo.livefootball.match.domain.repository

import com.diegopizzo.livefootball.match.domain.repository.model.MatchData

interface MatchRepository {
    suspend fun getMatches(
        date: String,
        leagueIds: List<Long> = emptyList(),
    ): Result<List<MatchData>>
}
