package com.diegopizzo.livefootball.match.domain.usecase

import com.diegopizzo.livefootball.match.domain.repository.model.AwayData
import com.diegopizzo.livefootball.match.domain.repository.model.GoalsData
import com.diegopizzo.livefootball.match.domain.repository.model.HomeData
import com.diegopizzo.livefootball.match.domain.repository.model.LeagueData
import com.diegopizzo.livefootball.match.domain.repository.model.MatchData
import com.diegopizzo.livefootball.match.domain.repository.model.MatchStatus
import com.diegopizzo.livefootball.match.domain.repository.model.StatusData
import com.diegopizzo.livefootball.match.domain.repository.model.TeamsData

internal val matchDataList: List<MatchData>
    get() {
        val default = MatchData(
            id = 8675,
            timezone = "UTC",
            date = "2024-08-25T16:30:00+00:00",
            timestampUtc = 0,
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
            league = LeagueData(
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
