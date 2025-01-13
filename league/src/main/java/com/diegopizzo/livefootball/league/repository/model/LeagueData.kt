package com.diegopizzo.livefootball.league.repository.model

import com.diegopizzo.livefootball.league.config.LeagueType

data class LeagueData(
    val id: Long,
    val name: String,
    val countryName: String? = null,
    val countryCode: String? = null,
    val type: LeagueType,
    val logo: String,
)
