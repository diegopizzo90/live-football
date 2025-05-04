package com.diegopizzo.livefootball.league.api.repository.store

import app.cash.sqldelight.db.SqlDriver
import com.diegopizzo.livefootball.league.api.config.CountryCode
import com.diegopizzo.livefootball.league.api.config.LeaguesAvailable
import com.diegopizzo.livefootball.test_utils.provideTestDriver
import database.LeagueEntity
import kotlinx.coroutines.test.runTest
import sqldelight.database.LeagueDatabase
import kotlin.test.AfterTest
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals

class LeagueDbRepositoryTest {

    private lateinit var driver: SqlDriver
    private lateinit var database: LeagueDatabase
    private lateinit var leagueDbRepository: LeagueDbRepository

    @BeforeTest
    suspend fun setup() {
        driver = provideTestDriver(LeagueDatabase.Schema)
        LeagueDatabase.Schema.create(driver)
        database = LeagueDatabase(driver)
        leagueDbRepository = LeagueDbRepositoryImpl(database.leagueQueries)
    }

    private suspend fun insertLeagues() {
        leagueDbRepository.insertLeague(leagueEntity1)
        leagueDbRepository.insertLeague(leagueEntity2)
    }

    @Test
    fun getLeagueAll_writeLeagueAndReadInList() = runTest {
        insertLeagues()
        val actualValue = leagueDbRepository.getAll()
        assertEquals(listOf(leagueEntity1, leagueEntity2), actualValue)
    }

    @Test
    fun getLeagueByName_writeLeagueAndReadInList() = runTest {
        insertLeagues()
        val actualValue = leagueDbRepository.getLeagueByName(LeaguesAvailable.SERIE_A.leagueName)
        assertEquals(leagueEntity1, actualValue)
    }

    @Test
    fun getLeaguesByCountry_writeLeagueAndReadInList() = runTest {
        insertLeagues()
        val actualValue = leagueDbRepository.getLeaguesByCountry(CountryCode.ENGLAND.code)
        assertEquals(listOf(leagueEntity2), actualValue)
    }

    @Test
    fun deleteLeague_writeLeagueAndReadInList() = runTest {
        getLeagueAll_writeLeagueAndReadInList()

        leagueDbRepository.deleteLeague(leagueEntity1)
        val actualValue = leagueDbRepository.getAll()
        assertEquals(listOf(leagueEntity2), actualValue)
    }

    @Test
    fun deleteAll_writeLeagueAndReadInList() = runTest {
        getLeagueAll_writeLeagueAndReadInList()

        leagueDbRepository.deleteAll()
        val actualValue = leagueDbRepository.getAll()
        assertEquals(emptyList<LeagueEntity>(), actualValue)
    }

    @Test
    fun deleteByName_writeLeagueAndReadInList() = runTest {
        getLeagueAll_writeLeagueAndReadInList()

        leagueDbRepository.deleteByName(LeaguesAvailable.PREMIER_LEAGUE.leagueName)
        val actualValue = leagueDbRepository.getAll()
        assertEquals(listOf(leagueEntity1), actualValue)
    }

    @AfterTest
    fun teardown() {
        driver.close()
    }
}
