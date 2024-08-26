package com.diegopizzo.match.api.repository.store

import com.diegopizzo.match.api.network.MatchApi
import com.diegopizzo.match.api.network.util.matchResponseDto
import com.diegopizzo.match.api.repository.store.data.matchDataList
import com.diegopizzo.match.api.repository.store.mapper.MatchDataMapperImpl
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

class MatchStoreTest {
    private lateinit var store: MatchStore
    private val api: MatchApi = mockk()

    @Before
    fun setUp() {
        store = MatchStoreImpl(
            api = api,
            mapper = MatchDataMapperImpl(),
            ttlCacheInMinutes = 10,
        )
    }

    @Test
    fun `should call api only once when called multiple times with the same parameters`() = runTest {
        val leagueId = 1L
        val from = "2024-01-01"
        val to = "2024-01-02"
        val season = "2024"

        coEvery { api.getMatches(leagueId, from, to, season) } returns Result.success(matchResponseDto)

        // First call
        store.getMatches(leagueId, from, to, season)
        // Second call
        val actualCached = store.getMatches(leagueId, from, to, season)
        val expected = Result.success(matchDataList)

        assertEquals(actualCached, expected)
        coVerify(exactly = 1) { api.getMatches(any(), any(), any(), any()) }
    }

    @Test
    fun `should call api multiple times when the parameters are different`() = runTest {
        val leagueId1 = 1L
        val leagueId2 = 2L

        val from = "2024-01-01"
        val to = "2024-01-02"
        val season = "2024"

        coEvery { api.getMatches(leagueId1, from, to, season) } returns Result.success(matchResponseDto)
        coEvery { api.getMatches(leagueId2, from, to, season) } returns Result.success(matchResponseDto)

        // First call
        store.getMatches(leagueId1, from, to, season)
        // Second call
        val actualCached = store.getMatches(leagueId2, from, to, season)
        val expected = Result.success(matchDataList)

        assertEquals(actualCached, expected)
        coVerify(exactly = 2) { api.getMatches(any(), any(), any(), any()) }
    }
}
