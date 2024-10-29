package com.diegopizzo.match.api.network

import com.diegopizzo.match.api.network.model.MatchResponseDto
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter

internal interface MatchApi {
    suspend fun getMatches(
        date: String,
        season: String,
    ): Result<MatchResponseDto>
}

internal class MatchApiImpl(private val client: HttpClient) : MatchApi {
    override suspend fun getMatches(
        date: String,
        season: String,
    ): Result<MatchResponseDto> {
        return runCatching {
            client.get("fixtures") {
                parameter("date", date)
                parameter("season", season)
            }.body()
        }
    }
}
