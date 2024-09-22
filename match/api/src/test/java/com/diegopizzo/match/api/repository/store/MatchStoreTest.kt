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
import kotlin.time.Duration.Companion.seconds

class MatchStoreTest {
    private lateinit var store: MatchStore
    private val api: MatchApi = mockk()

    @Before
    fun setUp() {
        store = MatchStoreImpl(
            api = api,
            mapper = MatchDataMapperImpl(),
            ttlCacheInSeconds = 10.seconds,
        )
    }

    @Test
    fun `should call api only once when called multiple times with the same parameters`() = runTest {
        val date = "2024-01-01"
        val season = "2024"

        coEvery { api.getMatches(date, season) } returns Result.success(matchResponseDto)

        // First call
        store.getMatches(date, season)
        // Second call
        val actualCached = store.getMatches(date, season)
        val expected = Result.success(matchDataList)

        assertEquals(actualCached, expected)
        coVerify(exactly = 1) { api.getMatches(any(), any()) }
    }

    @Test
    fun `should call api multiple times when the parameters are different`() = runTest {
        val date1 = "2024-02-01"
        val date2 = "2024-01-01"
        val season = "2024"

        coEvery { api.getMatches(date1, season) } returns Result.success(matchResponseDto)
        coEvery { api.getMatches(date2, season) } returns Result.success(matchResponseDto)

        // First call
        store.getMatches(date1, season)
        // Second call
        val actualCached = store.getMatches(date2, season)
        val expected = Result.success(matchDataList)

        assertEquals(actualCached, expected)
        coVerify(exactly = 2) { api.getMatches(any(), any()) }
    }
}
