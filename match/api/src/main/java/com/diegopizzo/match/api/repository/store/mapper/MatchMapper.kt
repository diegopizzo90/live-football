package com.diegopizzo.match.api.repository.store.mapper

import com.diegopizzo.match.api.network.model.GoalsDto
import com.diegopizzo.match.api.network.model.LeagueDto
import com.diegopizzo.match.api.network.model.MatchResponseDto
import com.diegopizzo.match.api.network.model.StatusDto
import com.diegopizzo.match.api.network.model.TeamsDto
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

internal interface MatchMapper {
    fun mapToMatchData(data: List<MatchEntity>): List<MatchData>
    fun mapToMatchData(dto: MatchResponseDto, date: String, season: String): MatchesResponseEntity
}

internal class MatchMapperImpl : MatchMapper {
    override fun mapToMatchData(data: List<MatchEntity>): List<MatchData> {
        return data.map {
            MatchData(
                id = it.matchId,
                timezone = it.timezone,
                date = it.date,
                timestampUtc = it.timestampUtc,
                status = mapToStatusData(it.status),
                league = mapToLeagueData(it.league),
                teams = mapToTeamsData(it.teams),
                goals = mapToGoalsData(it.goals),
            )
        }
    }

    override fun mapToMatchData(dto: MatchResponseDto, date: String, season: String): MatchesResponseEntity {
        val matchResponse = MatchResponseEntity(
            date = date,
            season = season,
        )
        return MatchesResponseEntity(
            matchResponse = matchResponse,
            matches = dto.response.map {
                MatchEntity(
                    matchId = it.match.id,
                    timezone = it.match.timezone,
                    date = it.match.date,
                    timestampUtc = it.match.timestampUtc,
                    status = mapToStatusEntity(it.match.status),
                    league = mapToLeagueEntity(it.league),
                    teams = mapToTeamsEntity(it.teams),
                    goals = mapToGoalsEntity(it.goals),
                )
            },
        )
    }

    private fun mapToTeamsData(team: TeamsEntity): TeamsData {
        with(team) {
            return TeamsData(
                home = HomeData(
                    id = home.idHome,
                    name = home.nameHome,
                    logo = home.logoHome,
                ),
                away = AwayData(
                    id = away.idAway,
                    name = away.nameAway,
                    logo = away.logoAway,
                ),
            )
        }
    }

    private fun mapToGoalsData(goals: GoalsEntity): GoalsData {
        with(goals) {
            return GoalsData(
                home = home,
                away = away,
            )
        }
    }

    private fun mapToStatusData(statusEntity: StatusEntity): StatusData {
        return StatusData(
            matchStatus = MatchStatus.fromShortNameValue(statusEntity.matchStatus),
            elapsed = statusEntity.elapsed,
        )
    }

    private fun mapToLeagueData(league: LeagueEntity): LeagueData {
        with(league) {
            return LeagueData(
                id = idLeague,
                name = nameLeague,
                logo = logoLeague,
            )
        }
    }

    private fun mapToTeamsEntity(teamsDto: TeamsDto): TeamsEntity {
        with(teamsDto) {
            return TeamsEntity(
                home = HomeEntity(
                    idHome = home.id,
                    nameHome = home.name,
                    logoHome = home.logo,
                ),
                away = AwayEntity(
                    idAway = away.id,
                    nameAway = away.name,
                    logoAway = away.logo,
                ),
            )
        }
    }

    private fun mapToGoalsEntity(goalsDto: GoalsDto): GoalsEntity {
        with(goalsDto) {
            return GoalsEntity(
                home = home,
                away = away,
            )
        }
    }

    private fun mapToStatusEntity(statusDto: StatusDto): StatusEntity {
        return StatusEntity(
            matchStatus = toMatchStatus(statusDto.status).shortName,
            elapsed = statusDto.elapsed,
        )
    }

    private fun mapToLeagueEntity(leagueDto: LeagueDto): LeagueEntity {
        with(leagueDto) {
            return LeagueEntity(
                idLeague = id,
                nameLeague = name,
                logoLeague = logo,
            )
        }
    }
}
