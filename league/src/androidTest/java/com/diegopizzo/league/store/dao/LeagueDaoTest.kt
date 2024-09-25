package com.diegopizzo.league.store.dao

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.diegopizzo.league.config.CountryCode
import com.diegopizzo.league.config.LeaguesAvailable
import com.diegopizzo.league.repository.store.dao.LeagueDao
import com.diegopizzo.league.repository.store.database.LeagueDatabase
import com.diegopizzo.league.repository.store.entity.LeagueEntity
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException

@RunWith(AndroidJUnit4::class)
class LeagueDaoTest {

    private lateinit var database: LeagueDatabase
    private lateinit var leagueDao: LeagueDao

    @Before
    fun setup() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        database = Room.inMemoryDatabaseBuilder(context, LeagueDatabase::class.java).build()
        leagueDao = database.leagueDao()
    }

    @Test
    fun getLeagueAll_writeLeagueAndReadInList() = runTest {
        leagueDao.insertLeague(leagueEntity1, leagueEntity2)
        val actualValue = leagueDao.getAll()
        assertEquals(listOf(leagueEntity1, leagueEntity2), actualValue)
    }

    @Test
    fun getLeagueByName_writeLeagueAndReadInList() = runTest {
        leagueDao.insertLeague(leagueEntity1, leagueEntity2)
        val actualValue = leagueDao.getLeagueByName(LeaguesAvailable.SERIE_A.leagueName)
        assertEquals(leagueEntity1, actualValue)
    }

    @Test
    fun getLeaguesByCountry_writeLeagueAndReadInList() = runTest {
        leagueDao.insertLeague(leagueEntity1, leagueEntity2)
        val actualValue = leagueDao.getLeaguesByCountry(CountryCode.ENGLAND.code)
        assertEquals(listOf(leagueEntity2), actualValue)
    }

    @Test
    fun deleteLeague_writeLeagueAndReadInList() = runTest {
        getLeagueAll_writeLeagueAndReadInList()

        leagueDao.deleteLeague(leagueEntity1)
        val actualValue = leagueDao.getAll()
        assertEquals(listOf(leagueEntity2), actualValue)
    }

    @Test
    fun deleteAll_writeLeagueAndReadInList() = runTest {
        getLeagueAll_writeLeagueAndReadInList()

        leagueDao.deleteAll()
        val actualValue = leagueDao.getAll()
        assertEquals(emptyList<LeagueEntity>(), actualValue)
    }

    @Test
    fun deleteByName_writeLeagueAndReadInList() = runTest {
        getLeagueAll_writeLeagueAndReadInList()

        leagueDao.deleteByName(LeaguesAvailable.PREMIER_LEAGUE.leagueName)
        val actualValue = leagueDao.getAll()
        assertEquals(listOf(leagueEntity1), actualValue)
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        database.close()
    }
}
