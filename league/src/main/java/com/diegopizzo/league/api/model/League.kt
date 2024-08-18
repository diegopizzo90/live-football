package com.diegopizzo.league.api.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class League(
    @SerialName("response") val response: List<LeagueResponse>,
)

@Serializable
data class LeagueResponse(
    @SerialName("league") val league: LeagueInfo,
    @SerialName("country") val country: CountryInfo,
)

@Serializable
data class LeagueInfo(
    @SerialName("id") val id: Int,
    @SerialName("name") val name: String,
    @SerialName("type") val type: String,
    @SerialName("logo") val logo: String,
)

@Serializable
data class CountryInfo(
    @SerialName("name") val name: String,
    @SerialName("code") val code: String? = null,
)
