package com.diegopizzo.livefootball.match.presentation.mapper

import com.diegopizzo.livefootball.core.utils.DateUtils
import com.diegopizzo.livefootball.core.utils.DateUtilsImpl
import com.diegopizzo.livefootball.match.presentation.util.datePickerList
import com.diegopizzo.livefootball.match.presentation.util.leagueViewDataList
import com.diegopizzo.livefootball.match.presentation.util.matchDataListUseCase
import com.diegopizzo.livefootball.match.presentation.util.matchViewDataList
import com.diegopizzo.livefootball.match.presentation.viewmodel.MatchFilterCriteria
import com.diegopizzo.livefootball.match.presentation.viewmodel.MatchViewState
import junit.framework.TestCase.assertEquals
import org.junit.Before
import org.junit.Test
import java.time.ZoneOffset
import java.util.Locale

class MatchViewDataMapperTest {

    private lateinit var mapper: MatchViewDataMapper
    private lateinit var dateUtils: DateUtils

    @Before
    fun setUp() {
        dateUtils = DateUtilsImpl(
            zoneId = ZoneOffset.UTC,
            locale = Locale.UK,
        )
        mapper = MatchViewDataMapperImpl(dateUtils)
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
