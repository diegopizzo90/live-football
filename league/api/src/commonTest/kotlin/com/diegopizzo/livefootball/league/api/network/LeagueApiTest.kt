package com.diegopizzo.livefootball.league.api.network

import com.diegopizzo.livefootball.league.api.config.LeaguesAvailable
import com.diegopizzo.livefootball.league.api.data.leaguesByNameResponse
import com.diegopizzo.livefootball.test_utils.mockHttpClient
import io.ktor.http.HttpStatusCode
import kotlinx.coroutines.test.runTest
import kotlin.test.Test
import kotlin.test.assertTrue

class LeagueApiTest {

    private lateinit var api: LeagueApi

    @Test
    fun `Should return league`() = runTest {
        api = LeagueApiImpl(mockHttpClient(leaguesByNameResponse))

        val results = api.getLeagueInfo(league = LeaguesAvailable.SERIE_A)

        assertTrue { results.isSuccess }
        assertTrue { results.getOrNull()!!.response.first().league.name == LeaguesAvailable.SERIE_A.leagueName }
        assertTrue { results.getOrNull()!!.response.first().league.id == 135L }
    }

    @Test
    fun `Should return error`() = runTest {
        api = LeagueApiImpl(
            mockHttpClient(
                httpStatusCode = HttpStatusCode.GatewayTimeout,
            ),
        )

        val results = api.getLeagueInfo(league = LeaguesAvailable.SERIE_A)

        assertTrue { results.isFailure }
    }
}
