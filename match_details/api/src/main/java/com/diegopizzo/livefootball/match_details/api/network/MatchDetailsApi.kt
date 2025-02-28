package com.diegopizzo.livefootball.match_details.api.network

import com.diegopizzo.livefootball.match_details.api.network.model.MatchDetailsMainDto
import com.diegopizzo.livefootball.match_details.api.network.model.MatchDetailsResponseDto
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter

internal interface MatchDetailsApi {
    suspend fun getMatchById(matchId: Long): Result<MatchDetailsMainDto>
}

internal class MatchDetailsApiImpl(private val client: HttpClient) : MatchDetailsApi {
    override suspend fun getMatchById(matchId: Long): Result<MatchDetailsMainDto> {
        return runCatching {
            client.get("fixtures") {
                parameter("id", matchId)
            }.body<MatchDetailsResponseDto>().response.first()
        }
    }
}
