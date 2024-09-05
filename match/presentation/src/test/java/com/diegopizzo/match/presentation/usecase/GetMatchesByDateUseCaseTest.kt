package com.diegopizzo.match.presentation.usecase

import com.diegopizzo.design.components.card.LFCardMatchViewData
import com.diegopizzo.league.repository.LeagueRepository
import com.diegopizzo.match.api.repository.MatchRepository
import com.diegopizzo.match.presentation.mapper.MatchViewDataMapperImpl
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class GetMatchesByDateUseCaseTest {

    private lateinit var getMatchesByDateUseCase: GetMatchesByDateUseCase
    private val matchRepository: MatchRepository = mockk()
    private val leagueRepository: LeagueRepository = mockk()

    @Before
    fun setUp() {
        getMatchesByDateUseCase = GetMatchesByDateUseCaseImpl(
            matchRepository = matchRepository,
            leagueRepository = leagueRepository,
            matchViewDataMapper = MatchViewDataMapperImpl(),
            refreshIntervalMs = 100000,
        )
    }

    @Test
    fun `get matches by date successfully and verify result`() = runTest {
        val date = "2024-01-01"
        coEvery { leagueRepository.getLeagues() }.returns(Result.success(leagues))
        coEvery { matchRepository.getMatches(1, date, date) }.returns(Result.success(matchDataList))
        coEvery { matchRepository.getMatches(2, date, date) }.returns(Result.success(matchDataList))

        val actual = getMatchesByDateUseCase(date, date).first()
        val expected = Result.success(matchViewDataList)

        assertEquals(expected, actual)
        coVerify(exactly = 1) { leagueRepository.getLeagues() }
        coVerify(exactly = 2) { matchRepository.getMatches(any(), date, date) }
    }

    @Test
    fun `get matches by date but leagues not found verify result`() = runTest {
        val error = Throwable("error")
        val date = "2024-01-01"
        coEvery { leagueRepository.getLeagues() }.returns(Result.failure(error))
        coEvery { matchRepository.getMatches(1, date, date) }.returns(Result.success(matchDataList))
        coEvery { matchRepository.getMatches(2, date, date) }.returns(Result.success(matchDataList))

        val actual = getMatchesByDateUseCase(date, date).first()
        val expected = Result.failure<List<LFCardMatchViewData>>(error)

        assertEquals(expected, actual)
        coVerify(exactly = 1) { leagueRepository.getLeagues() }
        coVerify(exactly = 0) { matchRepository.getMatches(any(), date, date) }
    }

    @Test
    fun `get matches by date but matches not found verify result`() = runTest {
        val date = "2024-01-01"
        val error = Throwable("error")
        coEvery { leagueRepository.getLeagues() }.returns(Result.success(leagues))
        coEvery { matchRepository.getMatches(1, date, date) }.returns(Result.success(matchDataList))
        coEvery { matchRepository.getMatches(2, date, date) }.returns(Result.failure(error))

        val actual = getMatchesByDateUseCase(date, date).first()
        val expected = Result.failure<List<LFCardMatchViewData>>(error)

        assertEquals(expected, actual)
        coVerify(exactly = 1) { leagueRepository.getLeagues() }
        coVerify(exactly = 2) { matchRepository.getMatches(any(), date, date) }
    }
}
