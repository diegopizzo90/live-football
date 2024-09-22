package com.diegopizzo.match.presentation.util

import com.diegopizzo.design.components.card.LFCardMatchViewData
import com.diegopizzo.design.components.cell.LFCellIconViewData
import com.diegopizzo.design.components.cell.LFCellMatchViewData
import com.diegopizzo.design.components.cell.LFCellResultViewData
import com.diegopizzo.design.components.chips.LFChipViewData
import com.diegopizzo.design.components.datepicker.LFDatePickerViewData
import com.diegopizzo.design.components.image.LFIconViewData
import com.diegopizzo.design.components.image.PainterViewData
import com.diegopizzo.league.config.CountryCode
import com.diegopizzo.league.config.LeagueType
import com.diegopizzo.league.config.LeaguesAvailable
import com.diegopizzo.league.repository.model.LeagueData
import com.diegopizzo.match.api.repository.store.model.AwayData
import com.diegopizzo.match.api.repository.store.model.GoalsData
import com.diegopizzo.match.api.repository.store.model.HomeData
import com.diegopizzo.match.api.repository.store.model.MatchData
import com.diegopizzo.match.api.repository.store.model.MatchStatus
import com.diegopizzo.match.api.repository.store.model.StatusData
import com.diegopizzo.match.api.repository.store.model.TeamsData

internal val leagues = listOf(
    LeagueData(
        id = 135,
        name = LeaguesAvailable.SERIE_A.leagueName,
        type = LeagueType.LEAGUE,
        logo = "logo",
        countryName = CountryCode.ITALY.name,
        countryCode = CountryCode.ITALY.code,
    ),
    LeagueData(
        id = 39,
        name = LeaguesAvailable.PREMIER_LEAGUE.leagueName,
        type = LeagueType.LEAGUE,
        logo = "logo",
        countryName = CountryCode.ENGLAND.name,
        countryCode = CountryCode.ENGLAND.code,
    ),
)

internal val matchDataList: List<MatchData>
    get() {
        val default = MatchData(
            id = 8675,
            timezone = "UTC",
            date = "2024-08-25T16:30:00+00:00",
            status = StatusData(
                matchStatus = MatchStatus.MATCH_FINISHED,
                elapsed = null,
            ),
            teams = TeamsData(
                home = HomeData(
                    id = 8140,
                    name = "name",
                    logo = "",
                ),
                away = AwayData(
                    id = 5080,
                    name = "name",
                    logo = "",
                ),
            ),
            league = com.diegopizzo.match.api.repository.store.model.LeagueData(
                id = 135,
                name = "Serie A",
                logo = "",
            ),
            goals = GoalsData(
                home = 0,
                away = 2,
            ),
        )
        return listOf(
            default,
            default.copy(
                status = StatusData(
                    matchStatus = MatchStatus.FIRST_HALF_KICK_OFF,
                    elapsed = 34,
                ),
                goals = GoalsData(
                    home = 0,
                    away = 0,
                ),
            ),
            default.copy(
                status = StatusData(
                    matchStatus = MatchStatus.NOT_STARTED,
                    elapsed = null,
                ),
                goals = GoalsData(
                    home = null,
                    away = null,
                ),
            ),
            default.copy(
                league = default.league.copy(id = 222),
            ),
        )
    }

internal val matchDataListUseCase: List<MatchData>
    get() {
        val default = MatchData(
            id = 8675,
            timezone = "UTC",
            date = "2024-08-25T16:30:00+00:00",
            status = StatusData(
                matchStatus = MatchStatus.MATCH_FINISHED,
                elapsed = null,
            ),
            teams = TeamsData(
                home = HomeData(
                    id = 8140,
                    name = "name",
                    logo = "",
                ),
                away = AwayData(
                    id = 5080,
                    name = "name",
                    logo = "",
                ),
            ),
            league = com.diegopizzo.match.api.repository.store.model.LeagueData(
                id = 135,
                name = "Serie A",
                logo = "",
            ),
            goals = GoalsData(
                home = 0,
                away = 2,
            ),
        )
        return listOf(
            default,
            default.copy(
                status = StatusData(
                    matchStatus = MatchStatus.FIRST_HALF_KICK_OFF,
                    elapsed = 34,
                ),
                goals = GoalsData(
                    home = 0,
                    away = 0,
                ),
            ),
            default.copy(
                status = StatusData(
                    matchStatus = MatchStatus.NOT_STARTED,
                    elapsed = null,
                ),
                goals = GoalsData(
                    home = null,
                    away = null,
                ),
            ),
        )
    }

internal val matchViewDataList: List<LFCardMatchViewData>
    get() {
        val default = LFCardMatchViewData(
            match = LFCellMatchViewData(
                id = 8675,
                leagueId = 135,
                cellIconHome = LFCellIconViewData(
                    icon = LFIconViewData(
                        painter = PainterViewData.urlPainter(""),
                    ),
                    text = "name",
                ),
                cellIconAway = LFCellIconViewData(
                    icon = LFIconViewData(
                        painter = PainterViewData.urlPainter(""),
                    ),
                    text = "name",
                ),
                time = "FT",
                result = LFCellResultViewData(
                    resultHome = "0",
                    resultAway = "2",
                ),
            ),
        )
        return listOf(
            default,
            default.copy(
                match = default.match.copy(
                    time = "34′",
                    result = default.match.result!!.copy(
                        resultHome = "0",
                        resultAway = "0",
                    ),
                    isLiveMatch = true,
                ),
            ),
            default.copy(
                match = default.match.copy(
                    time = "16:30",
                    result = default.match.result!!.copy(
                        resultHome = "",
                        resultAway = "",
                    ),
                ),
            ),
        )
    }

internal val leagueViewDataList: List<LFChipViewData>
    get() {
        return listOf(
            LFChipViewData(
                id = 0,
                text = "Live",
            ),
            LFChipViewData(
                id = 135,
                text = "Serie A",
                icon = LFIconViewData(
                    painter = PainterViewData.urlPainter(""),
                ),
            ),
        )
    }

internal val datePickerList: List<LFDatePickerViewData>
    get() {
        return listOf(
            LFDatePickerViewData(
                dayName = "Sat",
                dayNumber = "7",
                fullDate = "2024-09-07",
                millisUtc = 1725667200000,
            ),
            LFDatePickerViewData(
                dayName = "Sun",
                dayNumber = "8",
                fullDate = "2024-09-08",
                millisUtc = 1725753600000,
            ),
            LFDatePickerViewData(
                dayName = "Mon",
                dayNumber = "9",
                fullDate = "2024-09-09",
                millisUtc = 1725840000000,
            ),
            LFDatePickerViewData(
                dayName = "Tue",
                dayNumber = "10",
                fullDate = "2024-09-10",
                millisUtc = 1725926400000,
                selected = true,
            ),
            LFDatePickerViewData(
                dayName = "Wed",
                dayNumber = "11",
                fullDate = "2024-09-11",
                millisUtc = 1726012800000,
            ),
            LFDatePickerViewData(
                dayName = "Thu",
                dayNumber = "12",
                fullDate = "2024-09-12",
                millisUtc = 1726099200000,
            ),
            LFDatePickerViewData(
                dayName = "Fri",
                dayNumber = "13",
                fullDate = "2024-09-13",
                millisUtc = 1726185600000,
            ),
        )
    }
