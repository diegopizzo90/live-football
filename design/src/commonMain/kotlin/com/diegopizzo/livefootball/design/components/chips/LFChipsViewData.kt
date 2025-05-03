package com.diegopizzo.livefootball.design.components.chips

import com.diegopizzo.livefootball.design.components.image.LFIconViewData

data class LFChipViewData(
    val id: Long = 0,
    val icon: LFIconViewData? = null,
    val text: String,
    val isTextUppercase: Boolean = false,
    val selected: Boolean = false,
)
