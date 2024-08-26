package com.diegopizzo.fixture.api.repository.store.mapper

import com.diegopizzo.fixture.api.network.model.FixtureResponseDto
import com.diegopizzo.fixture.api.network.model.FixtureStatusDto
import com.diegopizzo.fixture.api.network.model.GoalsDto
import com.diegopizzo.fixture.api.network.model.StatusDto
import com.diegopizzo.fixture.api.network.model.TeamsDto
import com.diegopizzo.fixture.api.repository.store.model.AwayData
import com.diegopizzo.fixture.api.repository.store.model.FixtureData
import com.diegopizzo.fixture.api.repository.store.model.FixtureStatus
import com.diegopizzo.fixture.api.repository.store.model.GoalsData
import com.diegopizzo.fixture.api.repository.store.model.HomeData
import com.diegopizzo.fixture.api.repository.store.model.StatusData
import com.diegopizzo.fixture.api.repository.store.model.TeamsData


internal interface FixtureDataMapper {
    fun mapToFixturesData(dto: FixtureResponseDto): List<FixtureData>
}

internal class FixtureDataMapperImpl : FixtureDataMapper {
    override fun mapToFixturesData(dto: FixtureResponseDto): List<FixtureData> {
        return dto.response.map {
            FixtureData(
                id = it.fixture.id,
                timezone = it.fixture.timezone,
                date = it.fixture.date,
                status = mapToStatusData(it.fixture.status),
                teams = mapToTeamsData(it.teams),
                goals = mapToGoalsData(it.goals),
            )
        }
    }

    private fun mapToTeamsData(teamsDto: TeamsDto): TeamsData {
        with(teamsDto) {
            return TeamsData(
                home = HomeData(
                    id = home.id,
                    name = home.name,
                    logo = home.logo,
                ),
                away = AwayData(
                    id = away.id,
                    name = away.name,
                    logo = away.logo,
                ),
            )
        }
    }

    private fun mapToGoalsData(goalsDto: GoalsDto): GoalsData {
        with(goalsDto) {
            return GoalsData(
                home = home,
                away = away,
            )
        }
    }

    private fun mapToStatusData(statusDto: StatusDto): StatusData {
        val fixtureStatus = when (statusDto.status) {
            FixtureStatusDto.TIME_TO_BE_DEFINED -> FixtureStatus.TIME_TO_BE_DEFINED
            FixtureStatusDto.NOT_STARTED -> FixtureStatus.NOT_STARTED
            FixtureStatusDto.FIRST_HALF_KICK_OFF -> FixtureStatus.FIRST_HALF_KICK_OFF
            FixtureStatusDto.HALF_TIME -> FixtureStatus.HALF_TIME
            FixtureStatusDto.SECOND_HALF_STARTED -> FixtureStatus.SECOND_HALF_STARTED
            FixtureStatusDto.EXTRA_TIME -> FixtureStatus.EXTRA_TIME
            FixtureStatusDto.PENALTY_IN_PROGRESS -> FixtureStatus.PENALTY_IN_PROGRESS
            FixtureStatusDto.MATCH_FINISHED -> FixtureStatus.MATCH_FINISHED
            FixtureStatusDto.MATCH_FINISHED_AFTER_EXTRA_TIME -> FixtureStatus.MATCH_FINISHED_AFTER_EXTRA_TIME
            FixtureStatusDto.MATCH_FINISHED_AFTER_PENALTY -> FixtureStatus.MATCH_FINISHED_AFTER_PENALTY
            FixtureStatusDto.BREAK_TIME -> FixtureStatus.BREAK_TIME
            FixtureStatusDto.MATCH_SUSPENDED -> FixtureStatus.MATCH_SUSPENDED
            FixtureStatusDto.MATCH_INTERRUPTED -> FixtureStatus.MATCH_INTERRUPTED
            FixtureStatusDto.MATCH_POSTPONED -> FixtureStatus.MATCH_POSTPONED
            FixtureStatusDto.MATCH_CANCELED -> FixtureStatus.MATCH_CANCELED
            FixtureStatusDto.MATCH_ABANDONED -> FixtureStatus.MATCH_ABANDONED
            FixtureStatusDto.TECHNICAL_LOSS -> FixtureStatus.TECHNICAL_LOSS
            FixtureStatusDto.WALKOVER -> FixtureStatus.WALKOVER
            FixtureStatusDto.LIVE -> FixtureStatus.LIVE
            FixtureStatusDto.NOT_AVAILABLE -> FixtureStatus.NOT_AVAILABLE
            null -> FixtureStatus.NOT_AVAILABLE
        }
        return StatusData(
            status = fixtureStatus,
            elapsed = statusDto.elapsed,
        )
    }
}
