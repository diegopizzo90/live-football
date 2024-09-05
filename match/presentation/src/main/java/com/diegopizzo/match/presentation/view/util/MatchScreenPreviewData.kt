package com.diegopizzo.match.presentation.view.util

import com.diegopizzo.design.components.card.LFCardMatchViewData
import com.diegopizzo.design.components.cell.LFCellIconViewData
import com.diegopizzo.design.components.cell.LFCellMatchViewData
import com.diegopizzo.design.components.cell.LFCellResultViewData
import com.diegopizzo.design.components.image.LFIconViewData
import com.diegopizzo.design.components.image.PainterViewData

internal val matchViewDataList: List<LFCardMatchViewData>
    get() {
        val default = LFCardMatchViewData(
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
        return listOf(
            default,
            default.copy(
                match = default.match.copy(
                    time = "34′",
                    result = default.match.result!!.copy(
                        resultHome = "0",
                        resultAway = "0",
                    ),
                ),
                isLiveMatch = true,
            ),
            default.copy(
                match = default.match.copy(
                    time = "17:30",
                    result = default.match.result!!.copy(
                        resultHome = "",
                        resultAway = "",
                    ),
                ),
            ),
            default,
            default.copy(
                match = default.match.copy(
                    time = "34′",
                    result = default.match.result!!.copy(
                        resultHome = "0",
                        resultAway = "0",
                    ),
                ),
                isLiveMatch = true,
            ),
            default.copy(
                match = default.match.copy(
                    time = "17:30",
                    result = default.match.result!!.copy(
                        resultHome = "",
                        resultAway = "",
                    ),
                ),
            ),
        )
    }
