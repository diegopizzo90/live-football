package com.diegopizzo.fixture.api.network.util

import com.diegopizzo.fixture.api.network.model.AwayDto
import com.diegopizzo.fixture.api.network.model.FixtureDto
import com.diegopizzo.fixture.api.network.model.FixtureMainDto
import com.diegopizzo.fixture.api.network.model.FixtureResponseDto
import com.diegopizzo.fixture.api.network.model.FixtureStatusDto
import com.diegopizzo.fixture.api.network.model.GoalsDto
import com.diegopizzo.fixture.api.network.model.HomeDto
import com.diegopizzo.fixture.api.network.model.StatusDto
import com.diegopizzo.fixture.api.network.model.TeamsDto

internal val fixtureResponseDto = FixtureResponseDto(
    response = listOf(
        FixtureMainDto(
            fixture = FixtureDto(
                id = 1223606,
                timezone = "UTC",
                date = "2024-08-25T16:30:00+00:00",
                status = StatusDto(
                    status = FixtureStatusDto.NOT_STARTED,
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
            goals = GoalsDto(
                home = null,
                away = null,
            ),
        ),
        FixtureMainDto(
            fixture = FixtureDto(
                id = 1223609,
                timezone = "UTC",
                date = "2024-08-24T18:45:00+00:00",
                status = StatusDto(
                    status = FixtureStatusDto.MATCH_FINISHED,
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
            goals = GoalsDto(
                home = 0,
                away = 1,
            ),
        ),
        FixtureMainDto(
            fixture = FixtureDto(
                id = 1223610,
                timezone = "UTC",
                date = "2024-08-25T18:45:00+00:00",
                status = StatusDto(
                    status = FixtureStatusDto.NOT_STARTED,
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
            goals = GoalsDto(
                home = null,
                away = null,
            ),
        ),
        FixtureMainDto(
            fixture = FixtureDto(
                id = 1223614,
                timezone = "UTC",
                date = "2024-08-24T16:30:00+00:00",
                status = StatusDto(
                    status = FixtureStatusDto.MATCH_FINISHED,
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
            goals = GoalsDto(
                home = 2,
                away = 1,
            ),
        ),
    ),
)
