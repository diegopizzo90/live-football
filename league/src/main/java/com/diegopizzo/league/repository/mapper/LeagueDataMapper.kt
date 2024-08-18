package com.diegopizzo.league.repository.mapper

import com.diegopizzo.league.api.model.LeagueResponse
import com.diegopizzo.league.config.LeagueType
import com.diegopizzo.league.repository.model.LeagueData
import com.diegopizzo.league.repository.store.entity.LeagueEntity

class LeagueDataMapper {

    fun mapLeagueData(leagueResponse: LeagueResponse, leagueType: LeagueType): LeagueData {
        with(leagueResponse) {
            return LeagueData(
                id = league.id,
                name = league.name,
                type = leagueType,
                logo = league.logo,
                countryName = country.name,
                countryCode = country.code,
            )
        }
    }

    fun mapLeagueData(leagueEntity: LeagueEntity): LeagueData {
        with(leagueEntity) {
            LeagueResponse
            return LeagueData(
                id = leagueId.toInt(),
                name = name,
                type = leagueType,
                logo = logo,
                countryName = countryName,
                countryCode = countryCode,
            )
        }
    }

    fun mapLeagueEntity(leagueResponse: LeagueResponse): LeagueEntity {
        with(leagueResponse) {
            return LeagueEntity(
                leagueId = league.id.toLong(),
                name = league.name,
                logo = league.logo,
                countryName = country.name,
                countryCode = country.code,
                leagueType = LeagueType.fromValue(league.type) ?: LeagueType.LEAGUE,
            )
        }
    }
}
