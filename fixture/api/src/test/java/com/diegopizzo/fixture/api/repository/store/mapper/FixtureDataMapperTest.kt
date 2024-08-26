package com.diegopizzo.fixture.api.repository.store.mapper

import com.diegopizzo.fixture.api.network.util.fixtureResponseDto
import com.diegopizzo.fixture.api.repository.store.data.fixtureDataList
import junit.framework.TestCase.assertEquals
import org.junit.Before
import org.junit.Test

class FixtureDataMapperTest {

    private lateinit var mapper: FixtureDataMapper

    @Before
    fun setup() {
        mapper = FixtureDataMapperImpl()
    }

    @Test
    fun `should map fixture dto to fixture data`() {
        val actual = mapper.mapToFixturesData(fixtureResponseDto)
        val expected = fixtureDataList

        assertEquals(actual, expected)
    }
}
