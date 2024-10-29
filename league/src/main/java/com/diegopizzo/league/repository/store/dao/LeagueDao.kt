package com.diegopizzo.league.repository.store.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.diegopizzo.league.repository.store.entity.LeagueEntity

@Dao
internal interface LeagueDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertLeague(vararg league: LeagueEntity)

    @Query("SELECT * FROM league")
    suspend fun getAll(): List<LeagueEntity>?

    @Query("SELECT * FROM league WHERE league.name = :leagueName")
    suspend fun getLeagueByName(leagueName: String): LeagueEntity?

    @Query("SELECT * FROM league WHERE league.countryCode = :countryCode")
    suspend fun getLeaguesByCountry(countryCode: String): List<LeagueEntity>?

    @Delete
    suspend fun deleteLeague(league: LeagueEntity)

    @Query("DELETE FROM league")
    suspend fun deleteAll()

    @Query("DELETE FROM league WHERE league.name = :leagueName")
    suspend fun deleteByName(leagueName: String)
}
