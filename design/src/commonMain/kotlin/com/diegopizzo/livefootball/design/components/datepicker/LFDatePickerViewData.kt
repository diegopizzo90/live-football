package com.diegopizzo.livefootball.design.components.datepicker

data class LFDatePickerViewData(
    val dayName: String = "",
    val dayNumber: String = "",
    val fullDate: String,
    val millisUtc: Long? = null,
    val selected: Boolean = false,
)
