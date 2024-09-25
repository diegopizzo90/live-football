package com.diegopizzo.match.api.repository.store.entity

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import androidx.room.Relation
import com.diegopizzo.match.api.repository.store.model.MatchStatus

@Entity(
    tableName = "match_response",
    indices = [Index(value = ["date", "season"], unique = true)],
)
internal data class MatchResponseEntity(
    @PrimaryKey(autoGenerate = true) val matchResponseId: Long = 0L,
    val date: String,
    val season: String,
)

@Entity(
    tableName = "match",
    foreignKeys = [
        ForeignKey(
            entity = MatchResponseEntity::class,
            parentColumns = ["matchResponseId"],
            childColumns = ["matchResponseFkId"],
            onDelete = ForeignKey.CASCADE,
        ),
    ],
)
internal data class MatchEntity(
    @PrimaryKey val matchId: Long,
    val timezone: String,
    val date: String,
    val timestampUtc: Long,
    @Embedded val status: StatusEntity,
    @Embedded val league: LeagueEntity,
    @Embedded val teams: TeamsEntity,
    @Embedded val goals: GoalsEntity,
    val matchResponseFkId: Long = 0,
)

internal data class MatchesResponseEntity(
    @Embedded val matchResponse: MatchResponseEntity,
    @Relation(
        parentColumn = "matchResponseId",
        entityColumn = "matchResponseFkId",
    )
    val matches: List<MatchEntity>,
)

internal data class StatusEntity(
    val matchStatus: String? = MatchStatus.NOT_AVAILABLE.shortName,
    val elapsed: Int? = null,
)

internal data class LeagueEntity(
    val idLeague: Long,
    val nameLeague: String,
    val logoLeague: String? = null,
)

internal data class TeamsEntity(
    @Embedded val home: HomeEntity,
    @Embedded val away: AwayEntity,
)

internal data class HomeEntity(
    val idHome: Long,
    val nameHome: String,
    val logoHome: String,
)

internal data class AwayEntity(
    val idAway: Long,
    val nameAway: String,
    val logoAway: String,
)

internal data class GoalsEntity(
    val home: Int? = null,
    val away: Int? = null,
)
