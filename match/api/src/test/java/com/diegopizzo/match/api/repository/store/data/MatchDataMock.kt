package com.diegopizzo.match.api.repository.store.data

import com.diegopizzo.match.api.repository.store.model.AwayData
import com.diegopizzo.match.api.repository.store.model.GoalsData
import com.diegopizzo.match.api.repository.store.model.HomeData
import com.diegopizzo.match.api.repository.store.model.MatchData
import com.diegopizzo.match.api.repository.store.model.MatchStatus
import com.diegopizzo.match.api.repository.store.model.StatusData
import com.diegopizzo.match.api.repository.store.model.TeamsData

internal val matchDataList = listOf(
    MatchData(
        id = 1223606,
        timezone = "UTC",
        date = "2024-08-25T16:30:00+00:00",
        status = StatusData(
            status = MatchStatus.NOT_STARTED,
            elapsed = null,
        ),
        teams = TeamsData(
            home = HomeData(
                id = 502,
                name = "Fiorentina",
                logo = "https://media.api-sports.io/football/teams/502.png",
            ),
            away = AwayData(
                id = 517,
                name = "Venezia",
                logo = "https://media.api-sports.io/football/teams/517.png",
            ),
        ),
        goals = GoalsData(
            home = null,
            away = null,
        ),
    ),
    MatchData(
        id = 1223609,
        timezone = "UTC",
        date = "2024-08-24T18:45:00+00:00",
        status = StatusData(
            status = MatchStatus.MATCH_FINISHED,
            elapsed = 90,
        ),
        teams = TeamsData(
            home = HomeData(
                id = 1579,
                name = "Monza",
                logo = "https://media.api-sports.io/football/teams/1579.png",
            ),
            away = AwayData(
                id = 495,
                name = "Genoa",
                logo = "https://media.api-sports.io/football/teams/495.png",
            ),
        ),
        goals = GoalsData(
            home = 0,
            away = 1,
        ),
    ),
    MatchData(
        id = 1223610,
        timezone = "UTC",
        date = "2024-08-25T18:45:00+00:00",
        status = StatusData(
            status = MatchStatus.NOT_STARTED,
            elapsed = null,
        ),
        teams = TeamsData(
            home = HomeData(
                id = 492,
                name = "Napoli",
                logo = "https://media.api-sports.io/football/teams/492.png",
            ),
            away = AwayData(
                id = 500,
                name = "Bologna",
                logo = "https://media.api-sports.io/football/teams/500.png",
            ),
        ),
        goals = GoalsData(
            home = null,
            away = null,
        ),
    ),
    MatchData(
        id = 1223614,
        timezone = "UTC",
        date = "2024-08-24T16:30:00+00:00",
        status = StatusData(
            status = MatchStatus.MATCH_FINISHED,
            elapsed = 90,
        ),
        teams = TeamsData(
            home = HomeData(
                id = 494,
                name = "Udinese",
                logo = "https://media.api-sports.io/football/teams/494.png",
            ),
            away = AwayData(
                id = 487,
                name = "Lazio",
                logo = "https://media.api-sports.io/football/teams/487.png",
            ),
        ),
        goals = GoalsData(
            home = 2,
            away = 1,
        ),
    ),
)
