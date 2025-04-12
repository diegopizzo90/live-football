package com.diegopizzo.livefootball.match.presentation.usecase

import com.diegopizzo.livefootball.league.domain.usecase.GetLeagueIdsUseCase
import com.diegopizzo.livefootball.match.api.repository.MatchRepository
import com.diegopizzo.livefootball.match.api.repository.store.model.MatchData
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
    private val getLeagueIdsUseCase: GetLeagueIdsUseCase,
    private val refreshIntervalMs: Long,
) : GetMatchesByDateUseCase {

    override suspend operator fun invoke(
        date: String,
        forceRefresh: Boolean,
    ): Flow<Result<List<MatchData>>> {
        return flow {
            while (true) {
                val leagueIds = getLeagueIdsUseCase()
                if (leagueIds.isFailure) emit(Result.failure(leagueIds.exceptionOrNull()!!))

                val matches = matchRepository.getMatches(
                    date = date,
                    leagueIds = leagueIds.getOrThrow(),
                    forceRefresh = forceRefresh,
                )

                emit(matches)
                delay(refreshIntervalMs)
            }
        }.catch {
            emit(Result.failure(it))
        }
    }
}
