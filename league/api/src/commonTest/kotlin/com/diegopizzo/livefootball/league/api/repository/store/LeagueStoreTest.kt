package com.diegopizzo.livefootball.league.api.repository.store

import com.diegopizzo.livefootball.league.api.config.LeaguesAvailable
import com.diegopizzo.livefootball.league.api.data.leagueData
import com.diegopizzo.livefootball.league.api.data.leagueEntity
import com.diegopizzo.livefootball.league.api.data.leagueResponse
import com.diegopizzo.livefootball.league.api.network.LeagueApi
import com.diegopizzo.livefootball.league.api.repository.mapper.LeagueDataMapper
import dev.mokkery.answering.returns
import dev.mokkery.answering.sequentiallyReturns
import dev.mokkery.everySuspend
import dev.mokkery.matcher.any
import dev.mokkery.mock
import dev.mokkery.verify.VerifyMode
import dev.mokkery.verifySuspend
import kotlinx.coroutines.test.runTest
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals

class LeagueStoreTest {

    private lateinit var leagueStore: LeagueStore
    private val api: LeagueApi = mock()
    private val dao: LeagueDbRepository = mock()

    @BeforeTest
    fun setUp() {
        leagueStore = LeagueStoreImpl(api, dao, LeagueDataMapper(), 0)
    }

    @Test
    fun `should fetch from API and save to database`() = runTest {
        everySuspend { api.getLeagueInfo(LeaguesAvailable.SERIE_A) }.returns(
            Result.success(
                leagueResponse,
            ),
        )
        everySuspend { dao.getLeagueByName(any()) } sequentiallyReturns listOf(null, leagueEntity)
        everySuspend { dao.insertLeague(any()) } returns Unit

        val actual = leagueStore.getLeague(LeaguesAvailable.SERIE_A)
        assertEquals(Result.success(leagueData), actual)
        verifySuspend { api.getLeagueInfo(LeaguesAvailable.SERIE_A) }
        verifySuspend { dao.insertLeague(any()) }
    }

    @Test
    fun `should not fetch from API and get value from the database`() = runTest {
        everySuspend { api.getLeagueInfo(LeaguesAvailable.SERIE_A) } returns Result.success(leagueResponse)
        everySuspend { dao.getLeagueByName(any()) } returns leagueEntity
        everySuspend { dao.insertLeague(any()) } returns Unit

        val actual = leagueStore.getLeague(LeaguesAvailable.SERIE_A)
        assertEquals(Result.success(leagueData), actual)
        verifySuspend(mode = VerifyMode.not) { api.getLeagueInfo(LeaguesAvailable.SERIE_A) }
        verifySuspend(mode = VerifyMode.not) { dao.insertLeague(any()) }
    }

    @Test
    fun `should call deleteAll from DAO`() = runTest {
        everySuspend { dao.deleteAll() } returns Unit

        leagueStore.delete()
        verifySuspend { dao.deleteAll() }
    }
}
