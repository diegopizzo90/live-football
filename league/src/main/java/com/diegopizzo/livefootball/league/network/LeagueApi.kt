package com.diegopizzo.livefootball.league.network

import com.diegopizzo.livefootball.league.network.model.LeagueDto
import com.diegopizzo.livefootball.league.config.LeaguesAvailable
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter

internal interface LeagueApi {
    suspend fun getLeagueInfo(
        league: LeaguesAvailable,
    ): Result<LeagueDto>
}

internal class LeagueApiImpl(private val client: HttpClient) : LeagueApi {

    override suspend fun getLeagueInfo(
        league: LeaguesAvailable,
    ): Result<LeagueDto> {
        return runCatching {
            client.get("leagues") {
                league.run {
                    parameter("name", leagueName)
                    parameter("type", type.type)
                    countryCode?.let { parameter("code", countryCode.code) }
                }
            }.body()
        }
    }
}
