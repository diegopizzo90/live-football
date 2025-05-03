package com.diegopizzo.livefootball.match.domain.usecase

import com.diegopizzo.livefootball.match.domain.repository.MatchRepository
import com.diegopizzo.livefootball.match.domain.repository.model.MatchData
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

    @Before
    fun setUp() {
        getMatchesByDateUseCase = GetMatchesByDateUseCaseImpl(
            matchRepository = matchRepository,
            refreshIntervalMs = 100000,
        )
    }

    @Test
    fun `get matches by date successfully and verify result`() = runTest {
        val date = "2024-01-01"
        val leagueIds = listOf<Long>(135, 39)
        coEvery {
            matchRepository.getMatches(
                date,
                leagueIds,
            )
        }.returns(Result.success(matchDataList))

        val actual = getMatchesByDateUseCase(date, leagueIds).first()
        val expected = Result.success(matchDataList)

        assertEquals(expected, actual)
        coVerify(exactly = 1) { matchRepository.getMatches(date, leagueIds) }
    }

    @Test
    fun `get matches by date but matches not found verify result`() = runTest {
        val date = "2024-01-01"
        val error = Throwable("error")
        val leagueIds = listOf<Long>(135, 39)
        coEvery { matchRepository.getMatches(date, leagueIds) }.returns(Result.failure(error))

        val actual = getMatchesByDateUseCase(date, leagueIds).first()
        val expected = Result.failure<List<MatchData>>(error)

        assertEquals(expected, actual)
        coVerify(exactly = 1) { matchRepository.getMatches(date, leagueIds) }
    }
}
