package com.diegopizzo.fixture.api.network

import com.diegopizzo.core.config.mockHttpClient
import com.diegopizzo.fixture.api.network.util.fixtureApiResponse
import com.diegopizzo.fixture.api.network.util.fixtureResponseDto
import io.ktor.http.HttpStatusCode
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test

class FixtureApiTest {
    private lateinit var fixtureApi: FixtureApi

    @Test
    fun `should return fixtures successfully`() = runTest {
        fixtureApi = FixtureApiImpl(mockHttpClient(fixtureApiResponse))

        val actual = fixtureApi.getFixtures(
            leagueId = 0L,
            from = "2024-08-25",
            to = "2024-08-25",
            season = "2024",
        )

        val expected = Result.success(fixtureResponseDto)

        assertEquals(actual, expected)
    }

    @Test
    fun `should return error`() = runTest {
        fixtureApi = FixtureApiImpl(
            mockHttpClient(
                httpStatusCode = HttpStatusCode.InternalServerError,
            ),
        )

        val actual = fixtureApi.getFixtures(
            leagueId = 0L,
            from = "2024-08-25",
            to = "2024-08-25",
            season = "2024",
        )

        assert(actual.isFailure)
    }
}
