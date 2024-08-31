package com.diegopizzo.match.presentation.usecase

import com.diegopizzo.design.components.card.LFCardMatchViewData
import com.diegopizzo.design.components.cell.LFCellIconViewData
import com.diegopizzo.design.components.cell.LFCellMatchViewData
import com.diegopizzo.design.components.cell.LFCellResultViewData
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
        id = 1,
        name = LeaguesAvailable.SERIE_A.leagueName,
        type = LeagueType.LEAGUE,
        logo = "logo",
        countryName = CountryCode.ITALY.name,
        countryCode = CountryCode.ITALY.code,
    ),
    LeagueData(
        id = 2,
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
            )
        )
    }

internal val matchViewDataList: List<LFCardMatchViewData>
    get() {
        val default = LFCardMatchViewData(
            match = LFCellMatchViewData(
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
                ),
                isLiveMatch = true,
            ),
            default.copy(
                match = default.match.copy(
                    time = "17:30",
                    result = default.match.result!!.copy(
                        resultHome = "",
                        resultAway = "",
                    ),
                )
            ),
            default,
            default.copy(
                match = default.match.copy(
                    time = "34′",
                    result = default.match.result!!.copy(
                        resultHome = "0",
                        resultAway = "0",
                    ),
                ),
                isLiveMatch = true,
            ),
            default.copy(
                match = default.match.copy(
                    time = "17:30",
                    result = default.match.result!!.copy(
                        resultHome = "",
                        resultAway = "",
                    ),
                )
            ),
        )
    }
