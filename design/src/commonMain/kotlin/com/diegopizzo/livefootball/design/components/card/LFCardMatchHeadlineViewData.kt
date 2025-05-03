package com.diegopizzo.livefootball.design.components.card

import com.diegopizzo.livefootball.design.components.cell.LFCellHeadlineViewData
import com.diegopizzo.livefootball.design.components.cell.LFCellMatchViewData

data class LFCardMatchHeadlineViewData(
    val cellHeadlineViewData: LFCellHeadlineViewData,
    val matches: List<LFCellMatchViewData>,
)
