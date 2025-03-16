package com.diegopizzo.livefootball.league.api.store.dao

import com.diegopizzo.livefootball.league.api.config.CountryCode
import com.diegopizzo.livefootball.league.api.config.LeagueType
import com.diegopizzo.livefootball.league.api.config.LeaguesAvailable
import com.diegopizzo.livefootball.league.api.repository.store.entity.LeagueEntity

internal val leagueEntity1 = LeagueEntity(
    leagueId = 1,
    name = LeaguesAvailable.SERIE_A.leagueName,
    logo = "logo",
    countryName = CountryCode.ITALY.name,
    countryCode = CountryCode.ITALY.code,
    leagueType = LeagueType.LEAGUE,
)

internal val leagueEntity2 = LeagueEntity(
    leagueId = 2,
    name = LeaguesAvailable.PREMIER_LEAGUE.leagueName,
    logo = "logo",
    countryName = CountryCode.ENGLAND.name,
    countryCode = CountryCode.ENGLAND.code,
    leagueType = LeagueType.LEAGUE,
)
