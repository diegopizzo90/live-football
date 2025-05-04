package com.diegopizzo.livefootball.design.components.cell

data class LFCellMatchViewData(
    val id: Long = 0,
    val leagueId: Long = 0,
    val cellIconHome: LFCellIconViewData,
    val cellIconAway: LFCellIconViewData,
    val result: LFCellResultViewData? = null,
    val penaltyResult: LFCellResultViewData? = null,
    val time: String,
    val isLiveMatch: Boolean = false,
)

data class LFCellResultViewData(
    val resultHome: String,
    val resultAway: String,
)
