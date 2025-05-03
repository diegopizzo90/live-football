package com.diegopizzo.livefootball.match.presentation.viewmodel

import com.diegopizzo.livefootball.league.domain.usecase.GetLeagueIdsUseCase
import com.diegopizzo.livefootball.match.domain.repository.model.MatchData
import com.diegopizzo.livefootball.match.domain.usecase.GetMatchesByDateUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

interface MatchCoordinator {
    suspend fun fetchMatches(date: String): Flow<Result<List<MatchData>>>
}

internal class MatchCoordinatorImpl(
    private val getMatchesByDateUseCase: GetMatchesByDateUseCase,
    private val getLeagueIdsUseCase: GetLeagueIdsUseCase,
) : MatchCoordinator {
    override suspend fun fetchMatches(
        date: String,
    ): Flow<Result<List<MatchData>>> {
        val leagueIdsResult = getLeagueIdsUseCase.invoke()
        return if (leagueIdsResult.isSuccess) {
            getMatchesByDateUseCase(date, leagueIdsResult.getOrThrow())
        } else {
            flow { emit(Result.failure(leagueIdsResult.exceptionOrNull()!!)) }
        }
    }
}
