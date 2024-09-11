package com.diegopizzo.match.presentation.view.util

import com.diegopizzo.design.components.card.LFCardMatchViewData
import com.diegopizzo.design.components.cell.LFCellIconViewData
import com.diegopizzo.design.components.cell.LFCellMatchViewData
import com.diegopizzo.design.components.cell.LFCellResultViewData
import com.diegopizzo.design.components.chips.LFChipViewData
import com.diegopizzo.design.components.datepicker.LFDatePickerViewData
import com.diegopizzo.design.components.image.LFIconViewData
import com.diegopizzo.design.components.image.PainterViewData
import com.diegopizzo.design.theme.Icons
import com.diegopizzo.match.presentation.viewmodel.MatchViewState

internal val matchViewDataList: MatchViewState
    get() {
        val match = LFCardMatchViewData(
            match = LFCellMatchViewData(
                id = 1,
                cellIconHome = LFCellIconViewData(
                    icon = LFIconViewData(
                        painter = PainterViewData.urlPainter(""),
                    ),
                    text = "name",
                ),
                cellIconAway = LFCellIconViewData(
                    icon = LFIconViewData(
                        painter = PainterViewData.urlPainter(""),
                    ),
                    text = "name",
                ),
                time = "FT",
                result = LFCellResultViewData(
                    resultHome = "0",
                    resultAway = "2",
                ),
            ),
        )

        val matches = listOf(
            match,
            match.copy(
                match = match.match.copy(
                    id = 2,
                    time = "34′",
                    result = match.match.result!!.copy(
                        resultHome = "0",
                        resultAway = "0",
                    ),
                ),
                isLiveMatch = true,
            ),
            match.copy(
                match = match.match.copy(
                    id = 3,
                    time = "17:30",
                    result = match.match.result!!.copy(
                        resultHome = "",
                        resultAway = "",
                    ),
                ),
            ),
            match.copy(
                match = match.match.copy(
                    id = 4,
                ),
            ),
            match.copy(
                match = match.match.copy(
                    id = 5,
                    time = "34′",
                    result = match.match.result!!.copy(
                        resultHome = "0",
                        resultAway = "0",
                    ),
                ),
                isLiveMatch = true,
            ),
            match.copy(
                match = match.match.copy(
                    id = 6,
                    time = "17:30",
                    result = match.match.result!!.copy(
                        resultHome = "",
                        resultAway = "",
                    ),
                ),
            ),
        )

        val league = LFChipViewData(
            text = "Chip 1",
            icon = LFIconViewData(PainterViewData.drawableResourcePainter(Icons.ItalyFlag)),
            isTextUppercase = false,
            selected = false,
        )

        val leagues = listOf(
            league,
            league.copy(text = "Chip 2", selected = true, icon = null),
            league.copy(text = "Chip 3"),
            league.copy(text = "Chip 4", isTextUppercase = true),
            league.copy(text = "Chip 5"),
            league.copy(text = "Chip 6", selected = true, icon = null),
            league.copy(text = "Chip 7"),
            league.copy(text = "Chip 8", isTextUppercase = true),
            league.copy(text = "Chip 9"),
            league.copy(text = "Chip 10", selected = true, icon = null),
            league.copy(text = "Chip 11"),
            league.copy(text = "Chip 12", isTextUppercase = true),
        )

        val default = LFDatePickerViewData(
            dayName = "Mon",
            dayNumber = "1",
            fullDate = "2024-01-01",
        )

        val datePicker = listOf(
            default,
            default.copy(dayName = "Tue", dayNumber = "2"),
            default.copy(dayName = "Wed", dayNumber = "3"),
            default.copy(dayName = "Thu", dayNumber = "4", selected = true),
            default.copy(dayName = "Fri", dayNumber = "5"),
            default.copy(dayName = "Sat", dayNumber = "6"),
            default.copy(dayName = "Sun", dayNumber = "7"),
        )

        return MatchViewState(
            datePicker = datePicker,
            leagues = leagues,
            matches = matches,
        )
    }
