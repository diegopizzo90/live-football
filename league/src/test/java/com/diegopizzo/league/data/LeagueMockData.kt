package com.diegopizzo.league.data

import com.diegopizzo.league.api.model.CountryInfoDto
import com.diegopizzo.league.api.model.LeagueDto
import com.diegopizzo.league.api.model.LeagueInfoDto
import com.diegopizzo.league.api.model.LeagueResponseDto
import com.diegopizzo.league.config.CountryCode
import com.diegopizzo.league.config.LeagueType
import com.diegopizzo.league.config.LeaguesAvailable
import com.diegopizzo.league.repository.model.LeagueData
import com.diegopizzo.league.repository.store.entity.LeagueEntity

val leagueResponse =
    LeagueDto(
        response = listOf(
            LeagueResponseDto(
                league = LeagueInfoDto(
                    id = 1,
                    name = LeaguesAvailable.SERIE_A.leagueName,
                    type = LeagueType.LEAGUE.type,
                    logo = "logo",
                ),
                country = CountryInfoDto(
                    name = CountryCode.ITALY.name,
                    code = CountryCode.ITALY.code,
                ),
            ),
        ),
    )

val leagueData = LeagueData(
    id = 1,
    name = LeaguesAvailable.SERIE_A.leagueName,
    type = LeagueType.LEAGUE,
    logo = "logo",
    countryName = CountryCode.ITALY.name,
    countryCode = CountryCode.ITALY.code,
)

val leagueEntity = LeagueEntity(
    leagueId = 1,
    name = LeaguesAvailable.SERIE_A.leagueName,
    logo = "logo",
    countryName = CountryCode.ITALY.name,
    countryCode = CountryCode.ITALY.code,
    leagueType = LeagueType.LEAGUE,
)
