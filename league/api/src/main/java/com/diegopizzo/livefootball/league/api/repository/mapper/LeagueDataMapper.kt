package com.diegopizzo.livefootball.league.api.repository.mapper

import com.diegopizzo.livefootball.league.api.config.LeagueType
import com.diegopizzo.livefootball.league.api.network.model.LeagueResponseDto
import com.diegopizzo.livefootball.league.domain.repository.model.LeagueData
import com.diegopizzo.livefootball.league.domain.repository.model.LeagueTypeData
import database.LeagueEntity

internal class LeagueDataMapper {

    fun mapLeagueData(leagueEntity: LeagueEntity): LeagueData {
        with(leagueEntity) {
            return LeagueData(
                id = leagueId,
                name = name,
                type = LeagueTypeData.valueOf(leagueType),
                logo = logo,
                countryName = countryName,
                countryCode = countryCode,
            )
        }
    }

    fun mapLeagueEntity(leagueResponse: LeagueResponseDto): LeagueEntity {
        with(leagueResponse) {
            return LeagueEntity(
                leagueId = league.id,
                name = league.name,
                logo = league.logo,
                countryName = country.name,
                countryCode = country.code,
                leagueType = LeagueType.fromValue(league.type)?.name ?: LeagueType.LEAGUE.name,
            )
        }
    }
}
