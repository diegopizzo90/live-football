package com.diegopizzo.league.data

import com.diegopizzo.league.api.model.CountryInfo
import com.diegopizzo.league.api.model.League
import com.diegopizzo.league.api.model.LeagueInfo
import com.diegopizzo.league.api.model.LeagueResponse
import com.diegopizzo.league.config.CountryCode
import com.diegopizzo.league.config.LeagueType
import com.diegopizzo.league.config.LeaguesAvailable
import com.diegopizzo.league.repository.model.LeagueData
import com.diegopizzo.league.repository.store.entity.LeagueEntity

val leagueResponse =
    League(
        response = listOf(
            LeagueResponse(
                league = LeagueInfo(
                    id = 1,
                    name = LeaguesAvailable.SERIE_A.leagueName,
                    type = LeagueType.LEAGUE.type,
                    logo = "logo",
                ),
                country = CountryInfo(
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
