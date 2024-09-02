package com.diegopizzo.league.repository

import com.diegopizzo.league.data.leagueData
import com.diegopizzo.league.repository.mapper.LeagueDataMapper
import com.diegopizzo.league.repository.store.LeagueStore
import com.diegopizzo.test_utils.CoroutinesTestRule
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class LeagueRepositoryTest {

    private lateinit var repository: LeagueRepository
    private lateinit var mapper: LeagueDataMapper
    private val store: LeagueStore = mockk()

    @get:Rule
    val coroutinesTestRule = CoroutinesTestRule()

    @Before
    fun setUp() {
        mapper = LeagueDataMapper()
        repository = LeagueRepositoryImpl(store)
    }

    @Test
    fun `Should return league data`(): Unit = runTest {
        coEvery { store.getLeague(any()) }.returns(Result.success(leagueData))
        val league = repository.fetchLeagues()
        assert(league.isSuccess)
    }

    @Test
    fun `Should return error response`(): Unit = runTest {
        coEvery { store.getLeague(any()) }.returns(Result.failure(Throwable("Error")))
        val league = repository.fetchLeagues()
        assert(league.isFailure)
    }
}
