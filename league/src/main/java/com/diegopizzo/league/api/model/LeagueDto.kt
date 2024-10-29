package com.diegopizzo.league.api.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class LeagueDto(
    @SerialName("response") val response: List<LeagueResponseDto>,
)

@Serializable
data class LeagueResponseDto(
    @SerialName("league") val league: LeagueInfoDto,
    @SerialName("country") val country: CountryInfoDto,
)

@Serializable
data class LeagueInfoDto(
    @SerialName("id") val id: Long,
    @SerialName("name") val name: String,
    @SerialName("type") val type: String,
    @SerialName("logo") val logo: String,
)

@Serializable
data class CountryInfoDto(
    @SerialName("name") val name: String,
    @SerialName("code") val code: String? = null,
)
