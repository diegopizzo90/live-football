package com.diegopizzo.core.utils

import java.time.LocalDate

private const val END_OF_SEASON_MONTH = 7

fun getSeasonYear(): String {
    val currentDate = LocalDate.now()
    val currentYear = currentDate.year
    val seasonYear = if (currentDate.monthValue > END_OF_SEASON_MONTH) {
        currentYear
    } else {
        currentYear - 1
    }
    return seasonYear.toString()
}
