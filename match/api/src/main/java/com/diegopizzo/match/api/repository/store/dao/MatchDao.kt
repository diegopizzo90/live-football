package com.diegopizzo.match.api.repository.store.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.diegopizzo.match.api.repository.store.entity.MatchEntity
import com.diegopizzo.match.api.repository.store.entity.MatchResponseEntity
import com.diegopizzo.match.api.repository.store.entity.MatchesResponseEntity

@Dao
internal interface MatchDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMatchResponse(matchResponse: MatchResponseEntity): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMatches(vararg matches: MatchEntity)

    @Query("DELETE FROM `match_response`")
    suspend fun deleteAll()

    @Query("DELETE FROM `match_response` WHERE date = :matchDate AND season = :season")
    suspend fun deleteByDateAndSeason(matchDate: String, season: String)

    @Transaction
    @Query("SELECT * FROM `match_response` WHERE date = :matchDate AND season = :season")
    suspend fun getMatchesResponseByDateAndSeason(matchDate: String, season: String): MatchesResponseEntity?
}
