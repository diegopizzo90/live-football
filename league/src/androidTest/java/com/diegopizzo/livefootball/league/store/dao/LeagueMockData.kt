package com.diegopizzo.livefootball.league.store.dao

import com.diegopizzo.livefootball.league.config.CountryCode
import com.diegopizzo.livefootball.league.config.LeagueType
import com.diegopizzo.livefootball.league.config.LeaguesAvailable
import com.diegopizzo.livefootball.league.repository.store.entity.LeagueEntity

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
