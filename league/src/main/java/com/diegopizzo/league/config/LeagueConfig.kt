package com.diegopizzo.league.config

import com.diegopizzo.league.config.LeagueType.CUP
import com.diegopizzo.league.config.LeagueType.LEAGUE

enum class LeaguesAvailable(val leagueName: String, val type: LeagueType, val countryCode: CountryCode? = null) {
    SERIE_A("Serie A", LEAGUE, CountryCode.ITALY),
    COPPA_ITALIA("Coppa Italia", CUP, CountryCode.ITALY),
    PREMIER_LEAGUE("Premier League", LEAGUE, CountryCode.ENGLAND),
    LIGUE_1("Ligue 1", LEAGUE, CountryCode.FRANCE),
    LIGA("La Liga", LEAGUE, CountryCode.SPAIN),
    BUNDESLIGA("Bundesliga", LEAGUE, CountryCode.GERMANY),
    EUROPE_LEAGUE("UEFA Europa League", CUP),
    CHAMPIONS_LEAGUE("UEFA Champions League", CUP),
}

enum class LeagueType(val type: String) {
    CUP("cup"),
    LEAGUE("league"),
    ;

    companion object {
        fun fromValue(value: String): LeagueType? {
            return entries.find { it.type.contains(value, ignoreCase = true) }
        }
    }
}

enum class CountryCode(val code: String) {
    ITALY("IT"),
    FRANCE("FR"),
    GERMANY("DE"),
    SPAIN("ES"),
    ENGLAND("GB-ENG"),
}
