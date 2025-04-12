package com.diegopizzo.livefootball.league.domain.usecase

import com.diegopizzo.livefootball.league.domain.repository.LeagueRepository

interface GetLeaguesUseCase {
    suspend operator fun invoke(): Result<Unit>
}

internal class GetLeaguesUseCaseImpl(private val leagueRepository: LeagueRepository) : GetLeaguesUseCase {
    override suspend fun invoke(): Result<Unit> {
        return leagueRepository.fetchLeagues()
    }
}
