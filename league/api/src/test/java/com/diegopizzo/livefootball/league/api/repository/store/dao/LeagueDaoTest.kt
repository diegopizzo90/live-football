package com.diegopizzo.livefootball.league.api.repository.store.dao

import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.jdbc.sqlite.JdbcSqliteDriver
import com.diegopizzo.livefootball.league.api.config.CountryCode
import com.diegopizzo.livefootball.league.api.config.LeaguesAvailable
import database.LeagueEntity
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Test
import sqldelight.database.LeagueDatabase

class LeagueDaoTest {

    private lateinit var driver: SqlDriver
    private lateinit var database: LeagueDatabase
    private lateinit var leagueDbRepository: LeagueDbRepository

    @Before
    fun setup() {
        driver = JdbcSqliteDriver(JdbcSqliteDriver.IN_MEMORY)
        LeagueDatabase.Schema.create(driver)
        database = LeagueDatabase(driver)
        leagueDbRepository = LeagueDbRepositoryImpl(database.leagueQueries)
    }

    private fun insertLeagues() {
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

    @After
    fun teardown() {
        driver.close()
    }
}
