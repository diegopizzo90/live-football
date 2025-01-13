package com.diegopizzo.livefootball.match.api.repository.store

import com.diegopizzo.livefootball.core.utils.DateUtils
import com.diegopizzo.livefootball.match.api.network.MatchApi
import com.diegopizzo.livefootball.match.api.network.util.matchResponseDto
import com.diegopizzo.livefootball.match.api.repository.store.dao.MatchDbRepository
import com.diegopizzo.livefootball.match.api.repository.store.data.matchDataList
import com.diegopizzo.livefootball.match.api.repository.store.data.matchEntityList
import com.diegopizzo.livefootball.match.api.repository.store.data.matchesResponseEntity
import com.diegopizzo.livefootball.match.api.repository.store.mapper.MatchMapperImpl
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
    private val matchDbRepository: MatchDbRepository = mockk()
    private val dateUtils: DateUtils = mockk()

    @Before
    fun setUp() {
        store = MatchStoreImpl(
            api = api,
            matchMapper = MatchMapperImpl(),
            matchDbRepository = matchDbRepository,
            dateUtils = dateUtils,
            ttlCacheInSeconds = 30.seconds,
        )
    }

    @Test
    fun `should call api only once when called multiple times with the same parameters`() = runTest {
        val date = "2024-01-01"
        val season = "2024"

        coEvery { api.getMatches(date, season) } returns Result.success(matchResponseDto)
        coEvery { matchDbRepository.insertMatchesWithResponse(date, season, matchEntityList) }.returns(Unit)
        coEvery { matchDbRepository.getMatchesResponseByDateAndSeason(date, season) }.returnsMany(
            null,
            matchesResponseEntity,
        )
        coEvery { dateUtils.isToday(date) } returns false

        // First call
        store.getMatches(date, season)
        // Second call
        val actualCached = store.getMatches(date, season)
        val expected = Result.success(matchDataList)

        assertEquals(actualCached, expected)
        coVerify(exactly = 1) { api.getMatches(any(), any()) }
    }
}
