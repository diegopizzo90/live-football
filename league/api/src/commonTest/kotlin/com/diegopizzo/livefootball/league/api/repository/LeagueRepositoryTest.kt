package com.diegopizzo.livefootball.league.api.repository

import com.diegopizzo.livefootball.league.api.data.leagueData
import com.diegopizzo.livefootball.league.api.repository.mapper.LeagueDataMapper
import com.diegopizzo.livefootball.league.api.repository.store.LeagueStore
import com.diegopizzo.livefootball.league.domain.repository.LeagueRepository
import dev.mokkery.answering.returns
import dev.mokkery.everySuspend
import dev.mokkery.matcher.any
import dev.mokkery.mock
import kotlinx.coroutines.test.runTest
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertTrue

class LeagueRepositoryTest {

    private lateinit var repository: LeagueRepository
    private lateinit var mapper: LeagueDataMapper
    private val store: LeagueStore = mock()

    @BeforeTest
    fun setUp() {
        mapper = LeagueDataMapper()
        repository = LeagueRepositoryImpl(store)
    }

    @Test
    fun `Should return league data`() = runTest {
        everySuspend { store.getLeague(any()) } returns Result.success(leagueData)
        val league = repository.fetchLeagues()
        assertTrue { league.isSuccess }
    }

    @Test
    fun `Should return error response`() = runTest {
        everySuspend { store.getLeague(any()) } returns Result.failure(Throwable("Error"))
        val league = repository.fetchLeagues()
        assertTrue { league.isFailure }
    }
}
