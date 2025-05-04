package com.diegopizzo.livefootball.design.components.cell

import com.diegopizzo.livefootball.design.components.image.LFIconViewData

data class LFCellIconViewData(
    val icon: LFIconViewData,
    val text: String,
    val enabled: Boolean = true,
)
