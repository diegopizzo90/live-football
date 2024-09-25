package com.diegopizzo.match.api.repository.store.dao

import androidx.room.Transaction
import com.diegopizzo.match.api.repository.store.entity.MatchEntity
import com.diegopizzo.match.api.repository.store.entity.MatchResponseEntity
import com.diegopizzo.match.api.repository.store.entity.MatchesResponseEntity

internal interface MatchDbRepository {
    suspend fun deleteAll()
    suspend fun deleteByDateAndSeason(matchDate: String, season: String)
    suspend fun getMatchesResponseByDateAndSeason(matchDate: String, season: String): MatchesResponseEntity?

    @Transaction
    suspend fun insertMatchesWithResponse(matchDate: String, season: String, matches: List<MatchEntity>)
}

internal class MatchDbRepositoryImpl(
    private val matchDao: MatchDao,
) : MatchDbRepository {

    override suspend fun deleteAll() {
        matchDao.deleteAll()
    }

    override suspend fun deleteByDateAndSeason(matchDate: String, season: String) {
        matchDao.deleteByDateAndSeason(matchDate, season)
    }

    override suspend fun getMatchesResponseByDateAndSeason(matchDate: String, season: String): MatchesResponseEntity? {
        val matchesResponseEntity = matchDao.getMatchesResponseByDateAndSeason(matchDate, season)
        return matchesResponseEntity?.copy(
            matches = matchesResponseEntity.matches.sortedBy { it.date },
        )
    }

    @Transaction
    override suspend fun insertMatchesWithResponse(matchDate: String, season: String, matches: List<MatchEntity>) {
        val matchResponse = MatchResponseEntity(
            date = matchDate,
            season = season,
        )

        val matchResponseId = matchDao.insertMatchResponse(matchResponse)
        val matchesWithFK = matches.map {
            it.copy(matchResponseFkId = matchResponseId)
        }
        matchDao.insertMatches(*matchesWithFK.toTypedArray())
    }
}
