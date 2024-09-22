package com.diegopizzo.match.api.network

import com.diegopizzo.match.api.network.util.matchApiResponse
import com.diegopizzo.match.api.network.util.matchResponseDto
import com.diegopizzo.test_utils.mockHttpClient
import io.ktor.http.HttpStatusCode
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test

class MatchApiTest {
    private lateinit var matchApi: MatchApi

    @Test
    fun `should return matches successfully`() = runTest {
        matchApi = MatchApiImpl(mockHttpClient(matchApiResponse))

        val actual = matchApi.getMatches(
            date = "2024-08-25",
            season = "2024",
        )

        val expected = Result.success(matchResponseDto)

        assertEquals(actual, expected)
    }

    @Test
    fun `should return error`() = runTest {
        matchApi = MatchApiImpl(
            mockHttpClient(
                httpStatusCode = HttpStatusCode.InternalServerError,
            ),
        )

        val actual = matchApi.getMatches(
            date = "2024-08-25",
            season = "2024",
        )

        assert(actual.isFailure)
    }
}
