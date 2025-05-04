package com.diegopizzo.livefootball.match.api.repository.store

import com.diegopizzo.livefootball.match.api.repository.store.entity.MatchDbMapper.toEntity
import com.diegopizzo.livefootball.match.api.repository.store.entity.MatchEntity
import com.diegopizzo.livefootball.match.api.repository.store.entity.MatchesEntity
import com.diegopizzo.livefootball.match.api.repository.store.entity.insertInto
import database.MatchQueries

internal interface MatchDbRepository {
    suspend fun deleteAll()
    suspend fun deleteByDateAndSeason(matchDate: String, season: String)
    suspend fun getMatchesByDateAndSeason(matchDate: String, season: String): MatchesEntity?
    suspend fun insertMatches(matchDate: String, season: String, matches: List<MatchEntity>)
}

internal class MatchDbRepositoryImpl(
    private val matchQueries: MatchQueries,
) : MatchDbRepository {

    override suspend fun deleteAll() {
        matchQueries.deleteAllMatchDays()
    }

    override suspend fun deleteByDateAndSeason(matchDate: String, season: String) {
        matchQueries.deleteMatchDayByDateAndSeason(matchDate, season)
    }

    override suspend fun getMatchesByDateAndSeason(
        matchDate: String,
        season: String,
    ): MatchesEntity? {
        val matchDay = matchQueries.selectMatchDay(matchDate, season).executeAsOneOrNull()
            ?.toEntity()
            ?: return null

        val matches = matchQueries.selectMatchesByMatchDayId(matchDay.matchDayId)
            .executeAsList()
            .map { it.toEntity() }

        return MatchesEntity(matchDay, matches.sortedBy { it.date })
    }

    override suspend fun insertMatches(
        matchDate: String,
        season: String,
        matches: List<MatchEntity>,
    ) {
        matchQueries.transaction {
            matchQueries.insertMatchDay(matchDate, season)
            val response = matchQueries.selectMatchDay(matchDate, season).executeAsOne()

            matches.forEach { match ->
                match.copy(matchDayFkId = response.matchDayId)
                    .insertInto(matchQueries)
            }
        }
    }
}
