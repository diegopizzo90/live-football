package com.diegopizzo.livefootball.league.domain.repository

import com.diegopizzo.livefootball.league.api.repository.model.LeagueData

interface LeagueRepository {
    suspend fun fetchLeagues(): Result<Unit>
    suspend fun getLeagues(): Result<List<LeagueData>>
    suspend fun getLeagueIds(): Result<List<Long>>
}
