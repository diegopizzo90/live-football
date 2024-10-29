package com.diegopizzo.core.utils

import java.time.LocalDate

private const val END_OF_SEASON_MONTH = 7

fun getSeasonYear(): String {
    val currentDate = LocalDate.now()
    val isAfterSeasonEnd = currentDate.monthValue > END_OF_SEASON_MONTH
    val seasonYear = if (isAfterSeasonEnd) currentDate.year else currentDate.year - 1
    return seasonYear.toString()
}
