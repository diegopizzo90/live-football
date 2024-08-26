package com.diegopizzo.fixture.api.repository.store.model

internal data class FixtureData(
    val id: Long,
    val timezone: String,
    val date: String,
    val status: StatusData,
    val teams: TeamsData,
    val goals: GoalsData,
)

internal data class StatusData(
    val status: FixtureStatus? = FixtureStatus.NOT_AVAILABLE,
    val elapsed: Int? = null,
)

internal data class TeamsData(
    val home: HomeData,
    val away: AwayData,
)

internal data class HomeData(
    val id: Long,
    val name: String,
    val logo: String,
)

internal data class AwayData(
    val id: Long,
    val name: String,
    val logo: String,
)

internal data class GoalsData(
    val home: Int? = null,
    val away: Int? = null,
)

internal enum class FixtureStatus(val status: String) {
    TIME_TO_BE_DEFINED("TBD"),
    NOT_STARTED("NS"),
    FIRST_HALF_KICK_OFF("1H"),
    HALF_TIME("HT"),
    SECOND_HALF_STARTED("2H"),
    EXTRA_TIME("ET"),
    PENALTY_IN_PROGRESS("P"),
    MATCH_FINISHED("FT"),
    MATCH_FINISHED_AFTER_EXTRA_TIME("AET"),
    MATCH_FINISHED_AFTER_PENALTY("PEN"),
    BREAK_TIME("BT"),
    MATCH_SUSPENDED("SUSP"),
    MATCH_INTERRUPTED("INT"),
    MATCH_POSTPONED("PST"),
    MATCH_CANCELED("CANC"),
    MATCH_ABANDONED("ABD"),
    TECHNICAL_LOSS("AWD"),
    WALKOVER("WO"),
    LIVE("LIVE"),
    NOT_AVAILABLE("NA"),
    ;

    companion object {
        fun fromValue(stringValue: String?): FixtureStatus {
            return entries.firstOrNull { it.status == stringValue } ?: NOT_AVAILABLE
        }
    }
}
