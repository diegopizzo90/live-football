package com.diegopizzo.match.api.repository.store.model

data class MatchData(
    val id: Long,
    val timezone: String,
    val date: String,
    val timestampUtc: Long,
    val status: StatusData,
    val league: LeagueData,
    val teams: TeamsData,
    val goals: GoalsData,
    val penalty: PenaltyData? = null,
)

data class StatusData(
    val matchStatus: MatchStatus? = MatchStatus.NOT_AVAILABLE,
    val elapsed: Int? = null,
)

data class LeagueData(
    val id: Long,
    val name: String,
    val logo: String? = null,
)

data class TeamsData(
    val home: HomeData,
    val away: AwayData,
)

data class HomeData(
    val id: Long,
    val name: String,
    val logo: String,
)

data class AwayData(
    val id: Long,
    val name: String,
    val logo: String,
)

data class GoalsData(
    val home: Int? = null,
    val away: Int? = null,
)

data class PenaltyData(
    val home: Int? = null,
    val away: Int? = null,
)

enum class MatchStatus(val shortName: String, val isLive: Boolean, val isMatchPlaying: Boolean) {
    TIME_TO_BE_DEFINED(shortName = "TBD", isLive = false, isMatchPlaying = false),
    NOT_STARTED(shortName = "NS", isLive = true, isMatchPlaying = false),
    FIRST_HALF_KICK_OFF(shortName = "1H", isLive = true, isMatchPlaying = true),
    HALF_TIME(shortName = "HT", isLive = true, isMatchPlaying = false),
    SECOND_HALF_STARTED(shortName = "2H", isLive = true, isMatchPlaying = true),
    EXTRA_TIME(shortName = "ET", isLive = true, isMatchPlaying = true),
    PENALTY_IN_PROGRESS(shortName = "P", isLive = true, isMatchPlaying = true),
    MATCH_FINISHED(shortName = "FT", isLive = false, isMatchPlaying = false),
    MATCH_FINISHED_AFTER_EXTRA_TIME(shortName = "AET", isLive = false, isMatchPlaying = false),
    MATCH_FINISHED_AFTER_PENALTY(shortName = "PEN", isLive = false, isMatchPlaying = false),
    BREAK_TIME(shortName = "BT", isLive = true, isMatchPlaying = false),
    MATCH_SUSPENDED(shortName = "SUSP", isLive = true, isMatchPlaying = false),
    MATCH_INTERRUPTED(shortName = "INT", isLive = true, isMatchPlaying = false),
    MATCH_POSTPONED(shortName = "PST", isLive = true, isMatchPlaying = false),
    MATCH_CANCELED(shortName = "CANC", isLive = false, isMatchPlaying = false),
    MATCH_ABANDONED(shortName = "ABD", isLive = false, isMatchPlaying = false),
    TECHNICAL_LOSS(shortName = "AWD", isLive = true, isMatchPlaying = false),
    WALKOVER(shortName = "WO", isLive = true, isMatchPlaying = false),
    LIVE(shortName = "LIVE", isLive = true, isMatchPlaying = true),
    NOT_AVAILABLE(shortName = "NA", isLive = false, isMatchPlaying = false),
    ;

    companion object {
        fun fromShortNameValue(stringValue: String?): MatchStatus {
            return entries.firstOrNull { it.shortName == stringValue } ?: NOT_AVAILABLE
        }
    }
}
