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
        date: String,
        forceRefresh: Boolean = false,
    ): Flow<Result<List<MatchData>>>
}

internal class GetMatchesByDateUseCaseImpl(
    private val matchRepository: MatchRepository,
    private val leagueRepository: LeagueRepository,
    private val refreshIntervalMs: Long,
) : GetMatchesByDateUseCase {

    override suspend operator fun invoke(
        date: String,
        forceRefresh: Boolean,
    ): Flow<Result<List<MatchData>>> {
        return flow {
            while (true) {
                val leagueIds = leagueRepository.getLeagueIds()
                if (leagueIds.isFailure) emit(Result.failure(leagueIds.exceptionOrNull()!!))

                val matches = matchRepository.getMatches(
                    date = date,
                    forceRefresh = forceRefresh,
                )

                if (matches.isFailure) emit(Result.failure(matches.exceptionOrNull()!!))

                val filteredMatches = matches.getOrThrow().filter { match ->
                    match.league.id in leagueIds.getOrThrow()
                }.sortedBy { it.date }

                emit(Result.success(filteredMatches))
                delay(refreshIntervalMs)
            }
        }.catch {
            emit(Result.failure(it))
        }
    }
}
