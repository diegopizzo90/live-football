package com.diegopizzo.livefootball.league.api.data

import com.diegopizzo.livefootball.league.api.config.CountryCode
import com.diegopizzo.livefootball.league.api.config.LeagueType
import com.diegopizzo.livefootball.league.api.config.LeaguesAvailable
import com.diegopizzo.livefootball.league.api.network.model.CountryInfoDto
import com.diegopizzo.livefootball.league.api.network.model.LeagueDto
import com.diegopizzo.livefootball.league.api.network.model.LeagueInfoDto
import com.diegopizzo.livefootball.league.api.network.model.LeagueResponseDto
import com.diegopizzo.livefootball.league.domain.repository.model.LeagueData
import com.diegopizzo.livefootball.league.api.repository.store.entity.LeagueEntity
import com.diegopizzo.livefootball.league.domain.repository.model.LeagueTypeData

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
    type = LeagueTypeData.LEAGUE,
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
