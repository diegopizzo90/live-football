package com.diegopizzo.match.api.repository.store.mapper

import com.diegopizzo.match.api.network.model.MatchStatusDto
import com.diegopizzo.match.api.repository.store.model.MatchStatus

internal fun toMatchStatus(status: MatchStatusDto?): MatchStatus {
    return when (status) {
        MatchStatusDto.TIME_TO_BE_DEFINED -> MatchStatus.TIME_TO_BE_DEFINED
        MatchStatusDto.NOT_STARTED -> MatchStatus.NOT_STARTED
        MatchStatusDto.FIRST_HALF_KICK_OFF -> MatchStatus.FIRST_HALF_KICK_OFF
        MatchStatusDto.HALF_TIME -> MatchStatus.HALF_TIME
        MatchStatusDto.SECOND_HALF_STARTED -> MatchStatus.SECOND_HALF_STARTED
        MatchStatusDto.EXTRA_TIME -> MatchStatus.EXTRA_TIME
        MatchStatusDto.PENALTY_IN_PROGRESS -> MatchStatus.PENALTY_IN_PROGRESS
        MatchStatusDto.MATCH_FINISHED -> MatchStatus.MATCH_FINISHED
        MatchStatusDto.MATCH_FINISHED_AFTER_EXTRA_TIME -> MatchStatus.MATCH_FINISHED_AFTER_EXTRA_TIME
        MatchStatusDto.MATCH_FINISHED_AFTER_PENALTY -> MatchStatus.MATCH_FINISHED_AFTER_PENALTY
        MatchStatusDto.BREAK_TIME -> MatchStatus.BREAK_TIME
        MatchStatusDto.MATCH_SUSPENDED -> MatchStatus.MATCH_SUSPENDED
        MatchStatusDto.MATCH_INTERRUPTED -> MatchStatus.MATCH_INTERRUPTED
        MatchStatusDto.MATCH_POSTPONED -> MatchStatus.MATCH_POSTPONED
        MatchStatusDto.MATCH_CANCELED -> MatchStatus.MATCH_CANCELED
        MatchStatusDto.MATCH_ABANDONED -> MatchStatus.MATCH_ABANDONED
        MatchStatusDto.TECHNICAL_LOSS -> MatchStatus.TECHNICAL_LOSS
        MatchStatusDto.WALKOVER -> MatchStatus.WALKOVER
        MatchStatusDto.LIVE -> MatchStatus.LIVE
        MatchStatusDto.NOT_AVAILABLE -> MatchStatus.NOT_AVAILABLE
        null -> MatchStatus.NOT_AVAILABLE
    }
}
