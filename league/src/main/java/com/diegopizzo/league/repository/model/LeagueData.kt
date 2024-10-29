package com.diegopizzo.league.repository.model

import com.diegopizzo.league.config.LeagueType

data class LeagueData(
    val id: Long,
    val name: String,
    val countryName: String? = null,
    val countryCode: String? = null,
    val type: LeagueType,
    val logo: String,
)
