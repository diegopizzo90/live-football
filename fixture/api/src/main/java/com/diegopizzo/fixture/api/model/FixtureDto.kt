package com.diegopizzo.fixture.api.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class FixtureResponseDto(
    @SerialName("fixture") val fixture: FixtureDto,
    @SerialName("teams") val teams: TeamsDto,
    @SerialName("goals") val goals: GoalsDto,
)

@Serializable
data class FixtureDto(
    @SerialName("id") val id: Long,
    @SerialName("timezone") val timezone: String,
    @SerialName("date") val date: String,
    @SerialName("status") val status: StatusDto,
)

@Serializable
data class StatusDto(
    @SerialName("short") val statusValue: StatusValue? = StatusValue.NOT_AVAILABLE,
    @SerialName("elapsed") val elapsed: Int? = null,
)

@Serializable
data class TeamsDto(
    @SerialName("home") val home: HomeDto,
    @SerialName("away") val away: AwayDto,
)

@Serializable
data class HomeDto(
    @SerialName("id") val id: Long,
    @SerialName("name") val name: String,
    @SerialName("logo") val logo: String,
)

@Serializable
data class AwayDto(
    @SerialName("id") val id: Long,
    @SerialName("name") val name: String,
    @SerialName("logo") val logo: String,
)

@Serializable
data class GoalsDto(
    @SerialName("home") val home: Int? = null,
    @SerialName("away") val away: Int? = null,
)

@Serializable
enum class StatusValue(val status: String) {
    @SerialName("TBD")
    TIME_TO_BE_DEFINED("TBD"),

    @SerialName("NS")
    NOT_STARTED("NS"),

    @SerialName("1H")
    FIRST_HALF_KICK_OFF("1H"),

    @SerialName("HT")
    HALF_TIME("HT"),

    @SerialName("2H")
    SECOND_HALF_STARTED("2H"),

    @SerialName("ET")
    EXTRA_TIME("ET"),

    @SerialName("P")
    PENALTY_IN_PROGRESS("P"),

    @SerialName("FT")
    MATCH_FINISHED("FT"),

    @SerialName("AET")
    MATCH_FINISHED_AFTER_EXTRA_TIME("AET"),

    @SerialName("PEN")
    MATCH_FINISHED_AFTER_PENALTY("PEN"),

    @SerialName("BT")
    BREAK_TIME("BT"),

    @SerialName("SUSP")
    MATCH_SUSPENDED("SUSP"),

    @SerialName("INT")
    MATCH_INTERRUPTED("INT"),

    @SerialName("PST")
    MATCH_POSTPONED("PST"),

    @SerialName("CANC")
    MATCH_CANCELED("CANC"),

    @SerialName("ABD")
    MATCH_ABANDONED("ABD"),

    @SerialName("AWD")
    TECHNICAL_LOSS("AWD"),

    @SerialName("WO")
    WALKOVER("WO"),

    @SerialName("LIVE")
    LIVE("LIVE"),

    @SerialName("NA")
    NOT_AVAILABLE("NA");

    companion object {
        fun getStatusValue(stringValue: String?): StatusValue {
            return entries.firstOrNull { it.status == stringValue } ?: NOT_AVAILABLE
        }
    }
}
