package com.diegopizzo.fixture.api.network

import com.diegopizzo.fixture.api.network.model.FixtureResponseDto
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter


internal interface FixtureApi {
    suspend fun getFixtures(
        leagueId: Long,
        from: String,
        to: String,
        season: String,
    ): Result<FixtureResponseDto>
}

internal class FixtureApiImpl(private val client: HttpClient) : FixtureApi {
    override suspend fun getFixtures(
        leagueId: Long,
        from: String,
        to: String,
        season: String,
    ): Result<FixtureResponseDto> {
        return runCatching {
            client.get("fixtures") {
                parameter("league", leagueId.toString())
                parameter("from", from)
                parameter("to", to)
                parameter("season", season)
            }.body()
        }
    }
}
