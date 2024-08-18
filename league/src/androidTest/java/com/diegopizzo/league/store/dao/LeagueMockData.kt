package com.diegopizzo.league.store.dao

import com.diegopizzo.league.config.CountryCode
import com.diegopizzo.league.config.LeagueType
import com.diegopizzo.league.config.LeaguesAvailable
import com.diegopizzo.league.repository.store.entity.LeagueEntity

val leagueEntity1 = LeagueEntity(
    leagueId = 1,
    name = LeaguesAvailable.SERIE_A.leagueName,
    logo = "logo",
    countryName = CountryCode.ITALY.name,
    countryCode = CountryCode.ITALY.code,
    leagueType = LeagueType.LEAGUE,
)

val leagueEntity2 = LeagueEntity(
    leagueId = 2,
    name = LeaguesAvailable.PREMIER_LEAGUE.leagueName,
    logo = "logo",
    countryName = CountryCode.ENGLAND.name,
    countryCode = CountryCode.ENGLAND.code,
    leagueType = LeagueType.LEAGUE,
)
