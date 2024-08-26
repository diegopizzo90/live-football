package com.diegopizzo.fixture.api.repository.store

import com.diegopizzo.fixture.api.network.FixtureApi
import com.diegopizzo.fixture.api.network.util.fixtureResponseDto
import com.diegopizzo.fixture.api.repository.store.data.fixtureDataList
import com.diegopizzo.fixture.api.repository.store.mapper.FixtureDataMapperImpl
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

class FixtureStoreTest {
    private lateinit var store: FixtureStore
    private val api: FixtureApi = mockk()

    @Before
    fun setUp() {
        store = FixtureStoreImpl(
            api = api,
            mapper = FixtureDataMapperImpl(),
            ttlCacheInMinutes = 10,
        )
    }

    @Test
    fun `should call api only once when called multiple times with the same parameters`() = runTest {
        val leagueId = 1L
        val from = "2024-01-01"
        val to = "2024-01-02"
        val season = "2024"

        coEvery { api.getFixtures(leagueId, from, to, season) } returns Result.success(fixtureResponseDto)

        // First call
        store.getFixtures(leagueId, from, to, season)
        // Second call
        val actualCached = store.getFixtures(leagueId, from, to, season)
        val expected = Result.success(fixtureDataList)

        assertEquals(actualCached, expected)
        coVerify(exactly = 1) { api.getFixtures(any(), any(), any(), any()) }
    }

    @Test
    fun `should call api multiple times when the parameters are different`() = runTest {
        val leagueId1 = 1L
        val leagueId2 = 2L

        val from = "2024-01-01"
        val to = "2024-01-02"
        val season = "2024"

        coEvery { api.getFixtures(leagueId1, from, to, season) } returns Result.success(fixtureResponseDto)
        coEvery { api.getFixtures(leagueId2, from, to, season) } returns Result.success(fixtureResponseDto)


        // First call
        store.getFixtures(leagueId1, from, to, season)
        // Second call
        val actualCached = store.getFixtures(leagueId2, from, to, season)
        val expected = Result.success(fixtureDataList)

        assertEquals(actualCached, expected)
        coVerify(exactly = 2) { api.getFixtures(any(), any(), any(), any()) }
    }
}
