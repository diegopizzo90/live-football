package com.diegopizzo.livefootball.league.domain.repository.model

data class LeagueData(
    val id: Long,
    val name: String,
    val countryName: String? = null,
    val countryCode: String? = null,
    val type: LeagueTypeData,
    val logo: String,
)

enum class LeagueTypeData {
    CUP,
    LEAGUE,
}
