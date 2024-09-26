package com.diegopizzo.match.presentation.mapper

import com.diegopizzo.core.utils.DateUtils
import com.diegopizzo.design.components.card.LFCardMatchViewData
import com.diegopizzo.design.components.cell.LFCellIconViewData
import com.diegopizzo.design.components.cell.LFCellMatchViewData
import com.diegopizzo.design.components.cell.LFCellResultViewData
import com.diegopizzo.design.components.chips.LFChipViewData
import com.diegopizzo.design.components.datepicker.LFDatePickerViewData
import com.diegopizzo.design.components.image.LFIconViewData
import com.diegopizzo.design.components.image.PainterViewData
import com.diegopizzo.match.api.repository.store.model.LeagueData
import com.diegopizzo.match.api.repository.store.model.MatchData
import com.diegopizzo.match.api.repository.store.model.MatchStatus.EXTRA_TIME
import com.diegopizzo.match.api.repository.store.model.MatchStatus.FIRST_HALF_KICK_OFF
import com.diegopizzo.match.api.repository.store.model.MatchStatus.NOT_AVAILABLE
import com.diegopizzo.match.api.repository.store.model.MatchStatus.NOT_STARTED
import com.diegopizzo.match.api.repository.store.model.MatchStatus.SECOND_HALF_STARTED
import com.diegopizzo.match.api.repository.store.model.StatusData
import com.diegopizzo.match.presentation.mapper.MatchViewDataMapper.Companion.LIVE_EVENT
import com.diegopizzo.match.presentation.viewmodel.MatchFilterCriteria
import com.diegopizzo.match.presentation.viewmodel.MatchViewState

interface MatchViewDataMapper {
    fun mapViewData(
        matchDataList: List<MatchData>,
        currentMatchFilterCriteria: MatchFilterCriteria,
        date: String,
    ): MatchViewState

    companion object {
        const val LIVE_EVENT = "Live"
    }
}

internal class MatchViewDataMapperImpl(private val dateUtils: DateUtils) : MatchViewDataMapper {

    override fun mapViewData(
        matchDataList: List<MatchData>,
        currentMatchFilterCriteria: MatchFilterCriteria,
        date: String,
    ): MatchViewState {
        val leagueChips = matchDataList
            .distinctBy { it.league.id }
            .map { mapChipsViewData(it.league, it.league.id == currentMatchFilterCriteria.leagueId) }
            .toMutableList()

        if (matchDataList.any { isLive(it.status) }) {
            // Add Live chip
            leagueChips.add(
                0,
                LFChipViewData(
                    id = 0,
                    text = LIVE_EVENT,
                    selected = currentMatchFilterCriteria.isLive,
                ),
            )
        }

        val matchViewData = matchDataList.map { mapMatchViewData(it) }

        return MatchViewState(
            filterCriteria = currentMatchFilterCriteria,
            datePicker = mapDatePickerViewData(date),
            leagues = leagueChips,
            matches = matchViewData,
        )
    }

    private fun mapMatchViewData(data: MatchData): LFCardMatchViewData {
        with(data) {
            return LFCardMatchViewData(
                match = LFCellMatchViewData(
                    id = id,
                    leagueId = league.id,
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
                    result = goals.takeIf { it.home != null && it.away != null }?.let {
                        LFCellResultViewData(
                            resultHome = goals.home.toString(),
                            resultAway = goals.away.toString(),
                        )
                    },
                    penaltyResult = penalty?.takeIf { it.home != null && it.away != null }?.let {
                        LFCellResultViewData(
                            resultHome = it.home.toString(),
                            resultAway = it.away.toString(),
                        )
                    },
                    time = buildMatchTime(status, date),
                    isLiveMatch = isLive(status),
                ),
            )
        }
    }

    private fun mapDatePickerViewData(date: String): List<LFDatePickerViewData> {
        val dateList = dateUtils.generateDateList(date)
        return dateList.map {
            val fullDate = dateUtils.formatDate(it)
            val calendarInfo = dateUtils.getCalendarDisplayInfo(it)
            LFDatePickerViewData(
                dayName = calendarInfo.dayName,
                dayNumber = calendarInfo.dayNumber,
                fullDate = fullDate,
                millisUtc = dateUtils.toUtcMilliseconds(it),
                selected = fullDate == date,
            )
        }
    }

    private fun mapChipsViewData(data: LeagueData, isSelected: Boolean): LFChipViewData {
        with(data) {
            return LFChipViewData(
                id = id,
                icon = logo?.let { LFIconViewData(painter = PainterViewData.urlPainter(it)) },
                text = name,
                selected = isSelected,
            )
        }
    }

    private fun buildMatchTime(status: StatusData, date: String): String {
        return when (status.matchStatus) {
            FIRST_HALF_KICK_OFF, SECOND_HALF_STARTED, EXTRA_TIME -> "${status.elapsed}′"
            NOT_STARTED -> dateUtils.getLocalTimeFromUTCDate(date)
            else -> status.matchStatus?.shortName ?: NOT_AVAILABLE.shortName
        }
    }

    private fun isLive(status: StatusData): Boolean {
        return status.matchStatus?.isMatchPlaying == true
    }
}
