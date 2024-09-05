package com.diegopizzo.match.presentation.mapper

import com.diegopizzo.core.utils.DateUtils
import com.diegopizzo.design.components.card.LFCardMatchViewData
import com.diegopizzo.design.components.cell.LFCellIconViewData
import com.diegopizzo.design.components.cell.LFCellMatchViewData
import com.diegopizzo.design.components.cell.LFCellResultViewData
import com.diegopizzo.design.components.image.LFIconViewData
import com.diegopizzo.design.components.image.PainterViewData
import com.diegopizzo.match.api.repository.store.model.MatchData
import com.diegopizzo.match.api.repository.store.model.MatchStatus.EXTRA_TIME
import com.diegopizzo.match.api.repository.store.model.MatchStatus.FIRST_HALF_KICK_OFF
import com.diegopizzo.match.api.repository.store.model.MatchStatus.NOT_AVAILABLE
import com.diegopizzo.match.api.repository.store.model.MatchStatus.NOT_STARTED
import com.diegopizzo.match.api.repository.store.model.MatchStatus.SECOND_HALF_STARTED
import com.diegopizzo.match.api.repository.store.model.StatusData

interface MatchViewDataMapper {
    fun mapViewData(data: MatchData): LFCardMatchViewData
}

class MatchViewDataMapperImpl : MatchViewDataMapper {
    override fun mapViewData(data: MatchData): LFCardMatchViewData {
        with(data) {
            return LFCardMatchViewData(
                match = LFCellMatchViewData(
                    cellIconHome = LFCellIconViewData(
                        icon = LFIconViewData(
                            painter = PainterViewData.urlPainter(teams.home.logo),
                        ),
                        text = teams.home.name,
                    ),
                    cellIconAway = LFCellIconViewData(
                        icon = LFIconViewData(
                            painter = PainterViewData.urlPainter(teams.away.logo),
                        ),
                        text = teams.away.name,
                    ),
                    result = LFCellResultViewData(
                        resultHome = goals.home?.toString() ?: "",
                        resultAway = goals.away?.toString() ?: "",
                    ),
                    time = buildMatchTime(status, date),
                ),
                isLiveMatch = isLive(status),
            )
        }
    }

    private fun buildMatchTime(status: StatusData, date: String): String {
        return when (status.matchStatus) {
            FIRST_HALF_KICK_OFF, SECOND_HALF_STARTED, EXTRA_TIME -> "${status.elapsed}′"
            NOT_STARTED -> DateUtils.getLocalTimeFromUTCDate(date)
            else -> status.matchStatus?.shortName ?: NOT_AVAILABLE.shortName
        }
    }

    private fun isLive(status: StatusData): Boolean {
        val liveStatus = setOf(
            FIRST_HALF_KICK_OFF,
            SECOND_HALF_STARTED,
            EXTRA_TIME,
        )
        return status.matchStatus in liveStatus
    }
}
