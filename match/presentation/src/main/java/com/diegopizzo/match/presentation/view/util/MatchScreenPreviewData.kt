package com.diegopizzo.match.presentation.view.util

import com.diegopizzo.design.components.card.LFCardMatchViewData
import com.diegopizzo.design.components.cell.LFCellIconViewData
import com.diegopizzo.design.components.cell.LFCellMatchViewData
import com.diegopizzo.design.components.cell.LFCellResultViewData
import com.diegopizzo.design.components.chips.LFChipViewData
import com.diegopizzo.design.components.image.LFIconViewData
import com.diegopizzo.design.components.image.PainterViewData
import com.diegopizzo.design.theme.Icons
import com.diegopizzo.match.presentation.viewmodel.MatchViewState

internal val matchViewDataList: MatchViewState
    get() {
        val match = LFCardMatchViewData(
            match = LFCellMatchViewData(
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
                    time = "17:30",
                    result = match.match.result!!.copy(
                        resultHome = "",
                        resultAway = "",
                    ),
                ),
            ),
            match,
            match.copy(
                match = match.match.copy(
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

        return MatchViewState(
            leagues = leagues,
            matches = matches,
        )
    }
