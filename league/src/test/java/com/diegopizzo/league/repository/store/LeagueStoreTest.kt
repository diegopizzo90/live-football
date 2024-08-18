package com.diegopizzo.league.repository.store

import com.diegopizzo.league.api.LeagueApi
import com.diegopizzo.league.config.LeaguesAvailable
import com.diegopizzo.league.data.leagueData
import com.diegopizzo.league.data.leagueEntity
import com.diegopizzo.league.data.leagueResponse
import com.diegopizzo.league.repository.mapper.LeagueDataMapper
import com.diegopizzo.league.repository.store.dao.LeagueDao
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class LeagueStoreTest {

    private lateinit var leagueStore: LeagueStore
    private val api: LeagueApi = mockk()
    private val dao: LeagueDao = mockk()

    @Before
    fun setUp() {
        leagueStore = LeagueStoreImpl(api, dao, LeagueDataMapper(), 0)
    }

    @Test
    fun `should fetch from API and save to database`() = runTest {
        coEvery { api.getLeagueInfo(LeaguesAvailable.SERIE_A) }.returns(Result.success(leagueResponse))
        coEvery { dao.getLeagueByName(any()) }.returnsMany(listOf(null, leagueEntity))
        coEvery { dao.insertLeague(any()) }.returns(Unit)

        val actual = leagueStore.getLeague(LeaguesAvailable.SERIE_A)
        assertEquals(Result.success(leagueData), actual)
        coVerify { api.getLeagueInfo(LeaguesAvailable.SERIE_A) }
        coVerify { dao.insertLeague(any()) }
    }

    @Test
    fun `should not fetch from API and get value from the database`() = runTest {
        coEvery { api.getLeagueInfo(LeaguesAvailable.SERIE_A) }.returns(Result.success(leagueResponse))
        coEvery { dao.getLeagueByName(any()) }.returns(leagueEntity)
        coEvery { dao.insertLeague(any()) }.returns(Unit)

        val actual = leagueStore.getLeague(LeaguesAvailable.SERIE_A)
        assertEquals(Result.success(leagueData), actual)
        coVerify(exactly = 0) { api.getLeagueInfo(LeaguesAvailable.SERIE_A) }
        coVerify(exactly = 0) { dao.insertLeague(any()) }
    }

    @Test
    fun `should call deleteAll from DAO`() = runTest {
        coEvery { dao.deleteAll() }.returns(Unit)

        leagueStore.delete()
        coVerify { dao.deleteAll() }
    }
}
