package com.diegopizzo.match.api.network.util

import com.diegopizzo.match.api.network.model.AwayDto
import com.diegopizzo.match.api.network.model.GoalsDto
import com.diegopizzo.match.api.network.model.HomeDto
import com.diegopizzo.match.api.network.model.LeagueDto
import com.diegopizzo.match.api.network.model.MatchDto
import com.diegopizzo.match.api.network.model.MatchMainDto
import com.diegopizzo.match.api.network.model.MatchResponseDto
import com.diegopizzo.match.api.network.model.MatchStatusDto
import com.diegopizzo.match.api.network.model.StatusDto
import com.diegopizzo.match.api.network.model.TeamsDto

internal val matchResponseDto = MatchResponseDto(
    response = listOf(
        MatchMainDto(
            match = MatchDto(
                id = 1223606,
                timezone = "UTC",
                date = "2024-08-25T16:30:00+00:00",
                timestampUtc = 1724603400,
                status = StatusDto(
                    status = MatchStatusDto.NOT_STARTED,
                    elapsed = null,
                ),
            ),
            teams = TeamsDto(
                home = HomeDto(
                    id = 502,
                    name = "Fiorentina",
                    logo = "https://media.api-sports.io/football/teams/502.png",
                ),
                away = AwayDto(
                    id = 517,
                    name = "Venezia",
                    logo = "https://media.api-sports.io/football/teams/517.png",
                ),
            ),
            league = LeagueDto(
                id = 135,
                name = "Serie A",
                logo = "https://media.api-sports.io/football/leagues/135.png",
            ),
            goals = GoalsDto(
                home = null,
                away = null,
            ),
        ),
        MatchMainDto(
            match = MatchDto(
                id = 1223609,
                timezone = "UTC",
                date = "2024-08-24T18:45:00+00:00",
                timestampUtc = 1724525100,
                status = StatusDto(
                    status = MatchStatusDto.MATCH_FINISHED,
                    elapsed = 90,
                ),
            ),
            teams = TeamsDto(
                home = HomeDto(
                    id = 1579,
                    name = "Monza",
                    logo = "https://media.api-sports.io/football/teams/1579.png",
                ),
                away = AwayDto(
                    id = 495,
                    name = "Genoa",
                    logo = "https://media.api-sports.io/football/teams/495.png",
                ),
            ),
            league = LeagueDto(
                id = 135,
                name = "Serie A",
                logo = "https://media.api-sports.io/football/leagues/135.png",
            ),
            goals = GoalsDto(
                home = 0,
                away = 1,
            ),
        ),
        MatchMainDto(
            match = MatchDto(
                id = 1223610,
                timezone = "UTC",
                date = "2024-08-25T18:45:00+00:00",
                timestampUtc = 1724611500,
                status = StatusDto(
                    status = MatchStatusDto.NOT_STARTED,
                    elapsed = null,
                ),
            ),
            teams = TeamsDto(
                home = HomeDto(
                    id = 492,
                    name = "Napoli",
                    logo = "https://media.api-sports.io/football/teams/492.png",
                ),
                away = AwayDto(
                    id = 500,
                    name = "Bologna",
                    logo = "https://media.api-sports.io/football/teams/500.png",
                ),
            ),
            league = LeagueDto(
                id = 135,
                name = "Serie A",
                logo = "https://media.api-sports.io/football/leagues/135.png",
            ),
            goals = GoalsDto(
                home = null,
                away = null,
            ),
        ),
        MatchMainDto(
            match = MatchDto(
                id = 1223614,
                timezone = "UTC",
                date = "2024-08-24T16:30:00+00:00",
                timestampUtc = 1724517000,
                status = StatusDto(
                    status = MatchStatusDto.MATCH_FINISHED,
                    elapsed = 90,
                ),
            ),
            teams = TeamsDto(
                home = HomeDto(
                    id = 494,
                    name = "Udinese",
                    logo = "https://media.api-sports.io/football/teams/494.png",
                ),
                away = AwayDto(
                    id = 487,
                    name = "Lazio",
                    logo = "https://media.api-sports.io/football/teams/487.png",
                ),
            ),
            league = LeagueDto(
                id = 135,
                name = "Serie A",
                logo = "https://media.api-sports.io/football/leagues/135.png",
            ),
            goals = GoalsDto(
                home = 2,
                away = 1,
            ),
        ),
    ),
)
