package com.diegopizzo.livefootball.match.api.repository.store.entity

import database.MatchDayEntity as SqlDelightMatchDayEntity
import database.MatchEntity as SqlDelightMatchEntity

object MatchDbMapper {

    fun SqlDelightMatchEntity.toEntity(): MatchEntity {
        return MatchEntity(
            matchId = matchId,
            timezone = timezone,
            date = date,
            timestampUtc = timestampUtc,
            status = StatusEntity(
                matchStatus = matchStatus,
                elapsed = elapsed?.toInt(),
            ),
            league = LeagueEntity(
                idLeague = idLeague,
                nameLeague = nameLeague,
                logoLeague = logoLeague,
            ),
            teams = TeamsEntity(
                home = HomeEntity(
                    idHome = idHome,
                    nameHome = nameHome,
                    logoHome = logoHome,
                ),
                away = AwayEntity(
                    idAway = idAway,
                    nameAway = nameAway,
                    logoAway = logoAway,
                ),
            ),
            goals = GoalsEntity(
                home = goalsHome?.toInt(),
                away = goalsAway?.toInt(),
            ),
            penalty = PenaltyEntity(
                homePenaltyScore = homePenaltyScore?.toInt(),
                awayPenaltyScore = awayPenaltyScore?.toInt(),
            ),
            matchDayFkId = matchDayFkId,
        )
    }

    fun MatchEntity.toSqlDelightEntity(): SqlDelightMatchEntity {
        return SqlDelightMatchEntity(
            matchId = matchId,
            timezone = timezone,
            date = date,
            timestampUtc = timestampUtc,
            matchStatus = status.matchStatus,
            elapsed = status.elapsed?.toLong(),
            idLeague = league.idLeague,
            nameLeague = league.nameLeague,
            logoLeague = league.logoLeague,
            idHome = teams.home.idHome,
            nameHome = teams.home.nameHome,
            logoHome = teams.home.logoHome,
            idAway = teams.away.idAway,
            nameAway = teams.away.nameAway,
            logoAway = teams.away.logoAway,
            goalsHome = goals.home?.toLong(),
            goalsAway = goals.away?.toLong(),
            homePenaltyScore = penalty?.homePenaltyScore?.toLong(),
            awayPenaltyScore = penalty?.awayPenaltyScore?.toLong(),
            matchDayFkId = matchDayFkId,
        )
    }

    fun SqlDelightMatchDayEntity.toEntity(): MatchDayEntity {
        return MatchDayEntity(
            matchDayId = matchDayId,
            date = date,
            season = season,
        )
    }
}
