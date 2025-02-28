package com.diegopizzo.livefootball.match_details.api.network.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class MatchDetailsResponseDto(
    @SerialName("response") val response: List<MatchDetailsMainDto>,
)

@Serializable
internal data class MatchDetailsMainDto(
    @SerialName("fixture") val fixture: MatchDetailsDto,
    @SerialName("league") val league: LeagueDto,
    @SerialName("teams") val teams: TeamsDto,
    @SerialName("goals") val goals: GoalsDto,
    @SerialName("venue") val venue: VenueDto,
    @SerialName("events") val events: List<EventDto>? = null,
)

@Serializable
internal data class EventDto(
    @SerialName("time") val time: EventTime,
    @SerialName("team") val team: TeamDto,
    @SerialName("player") val player: PlayerDto,
    @SerialName("type") val type: EventTypeDto,
    @SerialName("detail") val detail: EventDetailDto,
)

@Serializable
internal data class EventTime(
    @SerialName("elapsed") val elapsed: Int? = null,
    @SerialName("extra") val extra: String? = null,
)

@Serializable
internal data class VenueDto(
    @SerialName("id") val id: Long,
    @SerialName("name") val name: String,
)

@Serializable
internal data class LeagueDto(
    @SerialName("id") val id: Long,
    @SerialName("name") val name: String,
    @SerialName("country") val country: String? = null,
    @SerialName("logo") val logo: String? = null,
)

@Serializable
internal data class MatchDetailsDto(
    @SerialName("id") val id: Long,
    @SerialName("timezone") val timezone: String,
    @SerialName("referee") val referee: String? = null,
    @SerialName("date") val date: String,
    @SerialName("timestamp") val timestampUtc: Long,
    @SerialName("status") val status: StatusDto,
)

@Serializable
internal data class StatusDto(
    @SerialName("long") val long: String? = null,
    @SerialName("elapsed") val elapsed: Int? = null,
)

@Serializable
internal data class TeamsDto(
    @SerialName("home") val home: TeamDto,
    @SerialName("away") val away: TeamDto,
)

@Serializable
internal data class TeamDto(
    @SerialName("id") val id: Long,
    @SerialName("name") val name: String,
    @SerialName("logo") val logo: String,
)

@Serializable
internal data class PlayerDto(
    @SerialName("id") val id: Long,
    @SerialName("name") val name: String,
)

@Serializable
internal data class GoalsDto(
    @SerialName("home") val home: Int? = null,
    @SerialName("away") val away: Int? = null,
)

@Serializable
internal enum class EventTypeDto {
    @SerialName("Goal")
    GOAL,

    @SerialName("Card")
    CARD,

    @SerialName("Subst")
    SUBSTITUTION,

    @SerialName("Var")
    VAR
}

@Serializable
internal sealed class EventDetailDto {
    @Serializable
    @SerialName("Yellow Card")
    data object YellowCard : EventDetailDto()

    @Serializable
    @SerialName("Red Card")
    data object RedCard : EventDetailDto()

    @Serializable
    data class GenericDetail(
        @SerialName("detail") val detail: String,
    ) : EventDetailDto()
}
