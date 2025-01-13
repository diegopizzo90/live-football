package com.diegopizzo.livefootball.league.data

import com.diegopizzo.livefootball.league.api.model.CountryInfoDto
import com.diegopizzo.livefootball.league.api.model.LeagueDto
import com.diegopizzo.livefootball.league.api.model.LeagueInfoDto
import com.diegopizzo.livefootball.league.api.model.LeagueResponseDto
import com.diegopizzo.livefootball.league.config.CountryCode
import com.diegopizzo.livefootball.league.config.LeagueType
import com.diegopizzo.livefootball.league.config.LeaguesAvailable
import com.diegopizzo.livefootball.league.repository.model.LeagueData
import com.diegopizzo.livefootball.league.repository.store.entity.LeagueEntity

internal val leagueResponse =
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

internal val leagueData = LeagueData(
    id = 1,
    name = LeaguesAvailable.SERIE_A.leagueName,
    type = LeagueType.LEAGUE,
    logo = "logo",
    countryName = CountryCode.ITALY.name,
    countryCode = CountryCode.ITALY.code,
)

internal val leagueEntity = LeagueEntity(
    leagueId = 1,
    name = LeaguesAvailable.SERIE_A.leagueName,
    logo = "logo",
    countryName = CountryCode.ITALY.name,
    countryCode = CountryCode.ITALY.code,
    leagueType = LeagueType.LEAGUE,
)
