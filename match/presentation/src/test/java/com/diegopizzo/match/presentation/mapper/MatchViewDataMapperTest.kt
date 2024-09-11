package com.diegopizzo.match.presentation.mapper

import com.diegopizzo.match.presentation.util.datePickerList
import com.diegopizzo.match.presentation.util.leagueViewDataList
import com.diegopizzo.match.presentation.util.matchDataListUseCase
import com.diegopizzo.match.presentation.util.matchViewDataList
import com.diegopizzo.match.presentation.viewmodel.MatchFilterCriteria
import com.diegopizzo.match.presentation.viewmodel.MatchViewState
import junit.framework.TestCase.assertEquals
import org.junit.Before
import org.junit.Test

class MatchViewDataMapperTest {

    private lateinit var mapper: MatchViewDataMapper

    @Before
    fun setUp() {
        mapper = MatchViewDataMapperImpl()
    }

    @Test
    fun testMapper() {
        val date = "2024-09-10"
        val actual = mapper.mapViewData(matchDataListUseCase, MatchFilterCriteria(), date)
        val expected = MatchViewState(
            datePicker = datePickerList,
            leagues = leagueViewDataList,
            matches = matchViewDataList,
        )

        assertEquals(expected, actual)
    }
}
