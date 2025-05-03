package com.diegopizzo.livefootball.match.presentation.mapper

import com.diegopizzo.livefootball.core.utils.DateUtils
import com.diegopizzo.livefootball.core.utils.DateUtilsImpl
import com.diegopizzo.livefootball.match.presentation.util.datePickerList
import com.diegopizzo.livefootball.match.presentation.util.leagueViewDataList
import com.diegopizzo.livefootball.match.presentation.util.matchDataList
import com.diegopizzo.livefootball.match.presentation.util.matchViewDataList
import com.diegopizzo.livefootball.match.presentation.viewmodel.MatchFilterCriteria
import com.diegopizzo.livefootball.match.presentation.viewmodel.MatchViewState
import kotlinx.datetime.TimeZone
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals

class MatchViewDataMapperTest {

    private lateinit var mapper: MatchViewDataMapper
    private lateinit var dateUtils: DateUtils

    @BeforeTest
    fun setUp() {
        dateUtils = DateUtilsImpl(timeZone = TimeZone.currentSystemDefault())
        mapper = MatchViewDataMapperImpl(dateUtils)
    }

    @Test
    fun testMapper() {
        val date = "2024-09-10"
        val actual = mapper.mapViewData(matchDataList, MatchFilterCriteria(), date)
        val expected = MatchViewState(
            datePicker = datePickerList,
            leagues = leagueViewDataList,
            matches = matchViewDataList,
        )

        assertEquals(expected, actual)
    }
}
