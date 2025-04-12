package com.diegopizzo.livefootball.match.domain.usecase

import com.diegopizzo.livefootball.match.domain.repository.MatchRepository
import com.diegopizzo.livefootball.match.domain.repository.model.MatchData
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

interface GetMatchesByDateUseCase {
    suspend operator fun invoke(
        date: String,
        leagueIds: List<Long>,
    ): Flow<Result<List<MatchData>>>
}

internal class GetMatchesByDateUseCaseImpl(
    private val matchRepository: MatchRepository,
    private val refreshIntervalMs: Long,
) : GetMatchesByDateUseCase {

    override suspend operator fun invoke(
        date: String,
        leagueIds: List<Long>,
    ): Flow<Result<List<MatchData>>> {
        return flow {
            while (true) {
                val matches = matchRepository.getMatches(
                    date = date,
                    leagueIds = leagueIds,
                )

                emit(matches)
                delay(refreshIntervalMs)
            }
        }
    }
}
