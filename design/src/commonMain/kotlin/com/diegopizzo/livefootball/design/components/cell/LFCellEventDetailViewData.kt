package com.diegopizzo.livefootball.design.components.cell

import com.diegopizzo.livefootball.design.components.image.LFIconViewData

data class LFCellEventDetailViewData(
    val time: String,
    val name: String,
    val icon: LFIconViewData,
    val score: String? = null,
    val isLeftAligned: Boolean = true,
)
