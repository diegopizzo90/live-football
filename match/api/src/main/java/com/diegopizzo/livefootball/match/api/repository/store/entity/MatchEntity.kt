package com.diegopizzo.livefootball.match.api.repository.store.entity

import com.diegopizzo.livefootball.match.api.repository.store.entity.MatchDbMapper.toSqlDelightEntity
import com.diegopizzo.livefootball.match.domain.repository.model.MatchStatus
import database.MatchQueries

data class MatchDayEntity(
    val matchDayId: Long,
    val date: String,
    val season: String,
)

data class MatchEntity(
    val matchId: Long,
    val timezone: String,
    val date: String,
    val timestampUtc: Long,
    val status: StatusEntity,
    val league: LeagueEntity,
    val teams: TeamsEntity,
    val goals: GoalsEntity,
    val penalty: PenaltyEntity? = null,
    val matchDayFkId: Long = 0L,
)

data class MatchesEntity(
    val matchDay: MatchDayEntity,
    val matches: List<MatchEntity>,
)

data class StatusEntity(
    val matchStatus: String? = MatchStatus.NOT_AVAILABLE.shortName,
    val elapsed: Int? = null,
)

data class LeagueEntity(
    val idLeague: Long,
    val nameLeague: String,
    val logoLeague: String? = null,
)

data class TeamsEntity(
    val home: HomeEntity,
    val away: AwayEntity,
)

data class HomeEntity(
    val idHome: Long,
    val nameHome: String,
    val logoHome: String,
)

data class AwayEntity(
    val idAway: Long,
    val nameAway: String,
    val logoAway: String,
)

data class GoalsEntity(
    val home: Int? = null,
    val away: Int? = null,
)

data class PenaltyEntity(
    val homePenaltyScore: Int? = null,
    val awayPenaltyScore: Int? = null,
)

fun MatchEntity.insertInto(matchQueries: MatchQueries) {
    val sql = this.toSqlDelightEntity()
    matchQueries.insertMatch(
        matchId = sql.matchId,
        timezone = sql.timezone,
        date = sql.date,
        timestampUtc = sql.timestampUtc,
        matchStatus = sql.matchStatus,
        elapsed = sql.elapsed,
        idLeague = sql.idLeague,
        nameLeague = sql.nameLeague,
        logoLeague = sql.logoLeague,
        idHome = sql.idHome,
        nameHome = sql.nameHome,
        logoHome = sql.logoHome,
        idAway = sql.idAway,
        nameAway = sql.nameAway,
        logoAway = sql.logoAway,
        goalsHome = sql.goalsHome,
        goalsAway = sql.goalsAway,
        homePenaltyScore = sql.homePenaltyScore,
        awayPenaltyScore = sql.awayPenaltyScore,
        matchDayFkId = sql.matchDayFkId,
    )
}

