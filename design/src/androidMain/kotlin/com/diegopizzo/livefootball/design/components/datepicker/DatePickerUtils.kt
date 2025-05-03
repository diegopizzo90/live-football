package com.diegopizzo.livefootball.design.components.datepicker

import com.diegopizzo.livefootball.core.utils.DateUtils
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.format.TextStyle
import java.util.Locale

object DatePickerUtils {

    private val defaultFormatter: DateTimeFormatter =
        DateTimeFormatter.ofPattern(DateUtils.DEFAULT_DATE_PATTERN)

    fun getDayNumber(dateString: String): String {
        val date = LocalDate.parse(dateString, defaultFormatter)
        return date.dayOfMonth.toString()
    }

    fun getDayName(dateString: String): String {
        val date = LocalDate.parse(dateString, defaultFormatter)
        return date.dayOfWeek.getDisplayName(TextStyle.SHORT, Locale.getDefault())
    }
}

