package com.diegopizzo.livefootball.design.components.drawer

import com.diegopizzo.livefootball.design.components.image.LFIconViewData

data class LFNavigationDrawerItemViewData(
    val label: String,
    val icon: LFIconViewData? = null,
    val selected: Boolean = false,
)
