package com.diegopizzo.match.api.network.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class MatchResponseDto(
    @SerialName("response") val response: List<MatchMainDto>,
)

@Serializable
internal data class MatchMainDto(
    @SerialName("fixture") val match: MatchDto,
    @SerialName("league") val league: LeagueDto,
    @SerialName("teams") val teams: TeamsDto,
    @SerialName("goals") val goals: GoalsDto,
)

@Serializable
data class LeagueDto(
    @SerialName("id") val id: Long,
    @SerialName("name") val name: String,
    @SerialName("logo") val logo: String? = null,
)

@Serializable
internal data class MatchDto(
    @SerialName("id") val id: Long,
    @SerialName("timezone") val timezone: String,
    @SerialName("date") val date: String,
    @SerialName("timestamp") val timestampUtc: Long,
    @SerialName("status") val status: StatusDto,
)

@Serializable
internal data class StatusDto(
    @SerialName("short") val status: MatchStatusDto? = MatchStatusDto.NOT_AVAILABLE,
    @SerialName("elapsed") val elapsed: Int? = null,
)

@Serializable
internal data class TeamsDto(
    @SerialName("home") val home: HomeDto,
    @SerialName("away") val away: AwayDto,
)

@Serializable
internal data class HomeDto(
    @SerialName("id") val id: Long,
    @SerialName("name") val name: String,
    @SerialName("logo") val logo: String,
)

@Serializable
internal data class AwayDto(
    @SerialName("id") val id: Long,
    @SerialName("name") val name: String,
    @SerialName("logo") val logo: String,
)

@Serializable
internal data class GoalsDto(
    @SerialName("home") val home: Int? = null,
    @SerialName("away") val away: Int? = null,
)

@Serializable
internal enum class MatchStatusDto(val status: String) {
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
    NOT_AVAILABLE("NA"),
    ;

    companion object {
        fun fromValue(stringValue: String?): MatchStatusDto {
            return entries.firstOrNull { it.status == stringValue } ?: NOT_AVAILABLE
        }
    }
}
