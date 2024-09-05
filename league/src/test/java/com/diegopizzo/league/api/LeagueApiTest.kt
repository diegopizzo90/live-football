package com.diegopizzo.league.api

import com.diegopizzo.league.config.LeaguesAvailable
import com.diegopizzo.league.data.leaguesByNameResponse
import com.diegopizzo.test_utils.mockHttpClient
import io.ktor.http.HttpStatusCode
import kotlinx.coroutines.test.runTest
import org.junit.Test

class LeagueApiTest {

    private lateinit var api: LeagueApi

    @Test
    fun `Should return league`(): Unit = runTest {
        api = LeagueApiImpl(mockHttpClient(leaguesByNameResponse))

        val results = api.getLeagueInfo(league = LeaguesAvailable.SERIE_A)

        assert(results.isSuccess)
        assert(results.getOrNull()!!.response.first().league.name == LeaguesAvailable.SERIE_A.leagueName)
        assert(results.getOrNull()!!.response.first().league.id == 135L)
    }

    @Test
    fun `Should return error`(): Unit = runTest {
        api = LeagueApiImpl(
            mockHttpClient(
                httpStatusCode = HttpStatusCode.GatewayTimeout,
            ),
        )

        val results = api.getLeagueInfo(league = LeaguesAvailable.SERIE_A)

        assert(results.isFailure)
    }
}
