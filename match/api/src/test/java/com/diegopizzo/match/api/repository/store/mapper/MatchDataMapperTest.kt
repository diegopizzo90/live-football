package com.diegopizzo.match.api.repository.store.mapper

import com.diegopizzo.match.api.network.util.matchResponseDto
import com.diegopizzo.match.api.repository.store.data.matchesResponseEntity
import junit.framework.TestCase.assertEquals
import org.junit.Before
import org.junit.Test

class MatchDataMapperTest {

    private lateinit var mapper: MatchMapper

    @Before
    fun setup() {
        mapper = MatchMapperImpl()
    }

    @Test
    fun `should map match dto to match data`() {
        val date = "2024-01-01"
        val season = "2024"
        val actual = mapper.mapToMatchData(matchResponseDto, date, season)
        val expected = matchesResponseEntity

        assertEquals(actual, expected)
    }
}
