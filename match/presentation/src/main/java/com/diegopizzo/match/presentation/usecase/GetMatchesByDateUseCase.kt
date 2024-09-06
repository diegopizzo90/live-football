package com.diegopizzo.match.presentation.usecase

import com.diegopizzo.league.repository.LeagueRepository
import com.diegopizzo.match.api.repository.MatchRepository
import com.diegopizzo.match.api.repository.store.model.MatchData
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow

interface GetMatchesByDateUseCase {
    suspend operator fun invoke(
        from: String,
        to: String,
        forceRefresh: Boolean = false,
    ): Flow<Result<List<MatchData>>>
}

internal class GetMatchesByDateUseCaseImpl(
    private val matchRepository: MatchRepository,
    private val leagueRepository: LeagueRepository,
    private val refreshIntervalMs: Long,
) : GetMatchesByDateUseCase {

    override suspend operator fun invoke(
        from: String,
        to: String,
        forceRefresh: Boolean,
    ): Flow<Result<List<MatchData>>> {
        return flow {
            while (true) {
                val leagueResults = leagueRepository.getLeagues()
                if (leagueResults.isFailure) emit(Result.failure(leagueResults.exceptionOrNull()!!))

                val matches = leagueResults.getOrThrow().flatMap { league ->
                    matchRepository.getMatches(
                        leagueId = league.id,
                        from = from,
                        to = to,
                        forceRefresh = forceRefresh,
                    ).getOrThrow()
                }

                val result = Result.success(matches)
                emit(result)
                delay(refreshIntervalMs)
            }
        }.catch {
            emit(Result.failure(it))
        }
    }
}
