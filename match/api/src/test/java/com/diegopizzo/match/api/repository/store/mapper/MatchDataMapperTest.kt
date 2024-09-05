package com.diegopizzo.match.api.repository.store.mapper

import com.diegopizzo.match.api.network.util.matchResponseDto
import com.diegopizzo.match.api.repository.store.data.matchDataList
import junit.framework.TestCase.assertEquals
import org.junit.Before
import org.junit.Test

class MatchDataMapperTest {

    private lateinit var mapper: MatchDataMapper

    @Before
    fun setup() {
        mapper = MatchDataMapperImpl()
    }

    @Test
    fun `should map match dto to match data`() {
        val actual = mapper.mapToMatchData(matchResponseDto)
        val expected = matchDataList

        assertEquals(actual, expected)
    }
}
