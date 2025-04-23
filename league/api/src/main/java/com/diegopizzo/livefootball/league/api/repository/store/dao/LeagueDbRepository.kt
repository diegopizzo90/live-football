package com.diegopizzo.livefootball.league.api.repository.store.dao

import database.LeagueEntity
import database.LeagueQueries

internal interface LeagueDbRepository {

    fun insertLeague(league: LeagueEntity)

    fun getAll(): List<LeagueEntity>

    fun getLeagueByName(leagueName: String): LeagueEntity?

    fun getLeaguesByCountry(countryCode: String): List<LeagueEntity>

    fun deleteLeague(league: LeagueEntity)

    fun deleteAll()

    fun deleteByName(leagueName: String)
}

internal class LeagueDbRepositoryImpl(private val queries: LeagueQueries) : LeagueDbRepository {

    override fun insertLeague(league: LeagueEntity) {
        queries.insertLeague(
            leagueId = league.leagueId,
            name = league.name,
            logo = league.logo,
            countryName = league.countryName,
            countryCode = league.countryCode,
            leagueType = league.leagueType,
        )
    }

    override fun getAll(): List<LeagueEntity> {
        return queries.selectAll().executeAsList()
    }

    override fun getLeagueByName(leagueName: String): LeagueEntity? {
        return queries.selectByName(leagueName).executeAsOneOrNull()
    }

    override fun getLeaguesByCountry(countryCode: String): List<LeagueEntity> {
        return queries.selectByCountry(countryCode).executeAsList()
    }

    override fun deleteLeague(league: LeagueEntity) {
        queries.deleteLeague(league.leagueId)
    }

    override fun deleteAll() {
        queries.deleteAll()
    }

    override fun deleteByName(leagueName: String) {
        queries.deleteByName(leagueName)
    }
}
