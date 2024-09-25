package com.diegopizzo.match.api.repository.store.data

import com.diegopizzo.match.api.repository.store.entity.AwayEntity
import com.diegopizzo.match.api.repository.store.entity.GoalsEntity
import com.diegopizzo.match.api.repository.store.entity.HomeEntity
import com.diegopizzo.match.api.repository.store.entity.LeagueEntity
import com.diegopizzo.match.api.repository.store.entity.MatchEntity
import com.diegopizzo.match.api.repository.store.entity.MatchResponseEntity
import com.diegopizzo.match.api.repository.store.entity.MatchesResponseEntity
import com.diegopizzo.match.api.repository.store.entity.StatusEntity
import com.diegopizzo.match.api.repository.store.entity.TeamsEntity
import com.diegopizzo.match.api.repository.store.model.AwayData
import com.diegopizzo.match.api.repository.store.model.GoalsData
import com.diegopizzo.match.api.repository.store.model.HomeData
import com.diegopizzo.match.api.repository.store.model.LeagueData
import com.diegopizzo.match.api.repository.store.model.MatchData
import com.diegopizzo.match.api.repository.store.model.MatchStatus
import com.diegopizzo.match.api.repository.store.model.StatusData
import com.diegopizzo.match.api.repository.store.model.TeamsData

internal val matchEntityList = listOf(
    MatchEntity(
        matchId = 1223606,
        timezone = "UTC",
        date = "2024-08-25T16:30:00+00:00",
        timestampUtc = 1724603400,
        status = StatusEntity(
            matchStatus = MatchStatus.NOT_STARTED.shortName,
            elapsed = null,
        ),
        teams = TeamsEntity(
            home = HomeEntity(
                idHome = 502,
                nameHome = "Fiorentina",
                logoHome = "https://media.api-sports.io/football/teams/502.png",
            ),
            away = AwayEntity(
                idAway = 517,
                nameAway = "Venezia",
                logoAway = "https://media.api-sports.io/football/teams/517.png",
            ),
        ),
        league = LeagueEntity(
            idLeague = 135,
            nameLeague = "Serie A",
            logoLeague = "https://media.api-sports.io/football/leagues/135.png",
        ),
        goals = GoalsEntity(
            home = null,
            away = null,
        ),
    ),
    MatchEntity(
        matchId = 1223609,
        timezone = "UTC",
        date = "2024-08-24T18:45:00+00:00",
        timestampUtc = 1724525100,
        status = StatusEntity(
            matchStatus = MatchStatus.MATCH_FINISHED.shortName,
            elapsed = 90,
        ),
        teams = TeamsEntity(
            home = HomeEntity(
                idHome = 1579,
                nameHome = "Monza",
                logoHome = "https://media.api-sports.io/football/teams/1579.png",
            ),
            away = AwayEntity(
                idAway = 495,
                nameAway = "Genoa",
                logoAway = "https://media.api-sports.io/football/teams/495.png",
            ),
        ),
        league = LeagueEntity(
            idLeague = 135,
            nameLeague = "Serie A",
            logoLeague = "https://media.api-sports.io/football/leagues/135.png",
        ),
        goals = GoalsEntity(
            home = 0,
            away = 1,
        ),
    ),
    MatchEntity(
        matchId = 1223610,
        timezone = "UTC",
        date = "2024-08-25T18:45:00+00:00",
        timestampUtc = 1724611500,
        status = StatusEntity(
            matchStatus = MatchStatus.NOT_STARTED.shortName,
            elapsed = null,
        ),
        teams = TeamsEntity(
            home = HomeEntity(
                idHome = 492,
                nameHome = "Napoli",
                logoHome = "https://media.api-sports.io/football/teams/492.png",
            ),
            away = AwayEntity(
                idAway = 500,
                nameAway = "Bologna",
                logoAway = "https://media.api-sports.io/football/teams/500.png",
            ),
        ),
        league = LeagueEntity(
            idLeague = 135,
            nameLeague = "Serie A",
            logoLeague = "https://media.api-sports.io/football/leagues/135.png",
        ),
        goals = GoalsEntity(
            home = null,
            away = null,
        ),
    ),
    MatchEntity(
        matchId = 1223614,
        timezone = "UTC",
        date = "2024-08-24T16:30:00+00:00",
        timestampUtc = 1724517000,
        status = StatusEntity(
            matchStatus = MatchStatus.MATCH_FINISHED.shortName,
            elapsed = 90,
        ),
        teams = TeamsEntity(
            home = HomeEntity(
                idHome = 494,
                nameHome = "Udinese",
                logoHome = "https://media.api-sports.io/football/teams/494.png",
            ),
            away = AwayEntity(
                idAway = 487,
                nameAway = "Lazio",
                logoAway = "https://media.api-sports.io/football/teams/487.png",
            ),
        ),
        league = LeagueEntity(
            idLeague = 135,
            nameLeague = "Serie A",
            logoLeague = "https://media.api-sports.io/football/leagues/135.png",
        ),
        goals = GoalsEntity(
            home = 2,
            away = 1,
        ),
    ),
)

internal val matchesResponseEntity = MatchesResponseEntity(
    matchResponse = MatchResponseEntity(
        date = "2024-01-01",
        season = "2024",
    ),
    matches = matchEntityList,
)

internal val matchDataList = listOf(
    MatchData(
        id = 1223606,
        timezone = "UTC",
        date = "2024-08-25T16:30:00+00:00",
        timestampUtc = 1724603400,
        status = StatusData(
            matchStatus = MatchStatus.NOT_STARTED,
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
        league = LeagueData(
            id = 135,
            name = "Serie A",
            logo = "https://media.api-sports.io/football/leagues/135.png",
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
        timestampUtc = 1724525100,
        status = StatusData(
            matchStatus = MatchStatus.MATCH_FINISHED,
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
        league = LeagueData(
            id = 135,
            name = "Serie A",
            logo = "https://media.api-sports.io/football/leagues/135.png",
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
        timestampUtc = 1724611500,
        status = StatusData(
            matchStatus = MatchStatus.NOT_STARTED,
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
        league = LeagueData(
            id = 135,
            name = "Serie A",
            logo = "https://media.api-sports.io/football/leagues/135.png",
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
        timestampUtc = 1724517000,
        status = StatusData(
            matchStatus = MatchStatus.MATCH_FINISHED,
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
        league = LeagueData(
            id = 135,
            name = "Serie A",
            logo = "https://media.api-sports.io/football/leagues/135.png",
        ),
        goals = GoalsData(
            home = 2,
            away = 1,
        ),
    ),
)
