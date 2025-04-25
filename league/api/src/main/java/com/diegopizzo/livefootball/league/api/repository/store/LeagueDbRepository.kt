package com.diegopizzo.livefootball.league.api.repository.store

import database.LeagueEntity
import database.LeagueQueries

internal interface LeagueDbRepository {

    suspend fun insertLeague(league: LeagueEntity)

    suspend fun getAll(): List<LeagueEntity>

    suspend fun getLeagueByName(leagueName: String): LeagueEntity?

    suspend fun getLeaguesByCountry(countryCode: String): List<LeagueEntity>

    suspend fun deleteLeague(league: LeagueEntity)

    suspend fun deleteAll()

    suspend fun deleteByName(leagueName: String)
}

internal class LeagueDbRepositoryImpl(private val queries: LeagueQueries) : LeagueDbRepository {

    override suspend fun insertLeague(league: LeagueEntity) {
        queries.insertLeague(
            leagueId = league.leagueId,
            name = league.name,
            logo = league.logo,
            countryName = league.countryName,
            countryCode = league.countryCode,
            leagueType = league.leagueType,
        )
    }

    override suspend fun getAll(): List<LeagueEntity> {
        return queries.selectAll().executeAsList()
    }

    override suspend fun getLeagueByName(leagueName: String): LeagueEntity? {
        return queries.selectByName(leagueName).executeAsOneOrNull()
    }

    override suspend fun getLeaguesByCountry(countryCode: String): List<LeagueEntity> {
        return queries.selectByCountry(countryCode).executeAsList()
    }

    override suspend fun deleteLeague(league: LeagueEntity) {
        queries.deleteLeague(league.leagueId)
    }

    override suspend fun deleteAll() {
        queries.deleteAll()
    }

    override suspend fun deleteByName(leagueName: String) {
        queries.deleteByName(leagueName)
    }
}
