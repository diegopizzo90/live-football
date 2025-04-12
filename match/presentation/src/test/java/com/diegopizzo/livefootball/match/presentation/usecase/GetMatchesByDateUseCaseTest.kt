package com.diegopizzo.livefootball.match.presentation.usecase

import com.diegopizzo.livefootball.design.components.card.LFCardMatchViewData
import com.diegopizzo.livefootball.league.domain.usecase.GetLeagueIdsUseCase
import com.diegopizzo.livefootball.match.api.repository.MatchRepository
import com.diegopizzo.livefootball.match.presentation.util.matchDataList
import com.diegopizzo.livefootball.match.presentation.util.matchDataListUseCase
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
    private val getLeagueIdsUseCase: GetLeagueIdsUseCase = mockk()

    @Before
    fun setUp() {
        getMatchesByDateUseCase = GetMatchesByDateUseCaseImpl(
            matchRepository = matchRepository,
            getLeagueIdsUseCase = getLeagueIdsUseCase,
            refreshIntervalMs = 100000,
        )
    }

    @Test
    fun `get matches by date successfully and verify result`() = runTest {
        val date = "2024-01-01"
        val leagueIds = listOf<Long>(135, 39)
        coEvery { getLeagueIdsUseCase() }.returns(Result.success(leagueIds))
        coEvery { matchRepository.getMatches(date, leagueIds) }.returns(Result.success(matchDataList))

        val actual = getMatchesByDateUseCase(date).first()
        val expected = Result.success(matchDataListUseCase)

        assertEquals(expected, actual)
        coVerify(exactly = 1) { getLeagueIdsUseCase() }
        coVerify(exactly = 1) { matchRepository.getMatches(date, leagueIds) }
    }

    @Test
    fun `get matches by date but leagues not found verify result`() = runTest {
        val error = Throwable("error")
        val date = "2024-01-01"
        val leagueIds = listOf<Long>(135, 39)
        coEvery { getLeagueIdsUseCase() }.returns(Result.failure(error))
        coEvery { matchRepository.getMatches(date, leagueIds) }.returns(Result.success(matchDataList))

        val actual = getMatchesByDateUseCase(date).first()
        val expected = Result.failure<List<LFCardMatchViewData>>(error)

        assertEquals(expected, actual)
        coVerify(exactly = 1) { getLeagueIdsUseCase() }
        coVerify(exactly = 0) { matchRepository.getMatches(date, leagueIds) }
    }

    @Test
    fun `get matches by date but matches not found verify result`() = runTest {
        val date = "2024-01-01"
        val error = Throwable("error")
        val leagueIds = listOf<Long>(135, 39)
        coEvery { getLeagueIdsUseCase() }.returns(Result.success(leagueIds))
        coEvery { matchRepository.getMatches(date, leagueIds) }.returns(Result.failure(error))

        val actual = getMatchesByDateUseCase(date).first()
        val expected = Result.failure<List<LFCardMatchViewData>>(error)

        assertEquals(expected, actual)
        coVerify(exactly = 1) { getLeagueIdsUseCase() }
        coVerify(exactly = 1) { matchRepository.getMatches(date, leagueIds) }
    }
}
