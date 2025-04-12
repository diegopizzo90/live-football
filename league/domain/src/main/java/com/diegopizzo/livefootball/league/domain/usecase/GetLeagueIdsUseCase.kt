package com.diegopizzo.livefootball.league.domain.usecase

import com.diegopizzo.livefootball.league.domain.repository.LeagueRepository

interface GetLeagueIdsUseCase {
    suspend operator fun invoke(): Result<List<Long>>
}

internal class GetLeagueIdsUseCaseImpl(private val leagueRepository: LeagueRepository) : GetLeagueIdsUseCase {
    override suspend fun invoke(): Result<List<Long>> {
        return leagueRepository.getLeagueIds()
    }
}
