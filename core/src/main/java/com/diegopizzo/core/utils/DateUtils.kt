package com.diegopizzo.core.utils

import java.time.Instant
import java.time.LocalDate
import java.time.Year
import java.time.ZoneId
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter

object DateUtils {

    private const val TIME_PATTERN = "HH:mm"
    const val DATE_PATTERN = "yyyy-MM-dd"
    const val MONTH_YEAR_PATTERN = "MMMM yyyy"
    private fun convertUtcDateTimeToLocal(
        utcDate: String,
        timeZone: ZoneId,
        pattern: String,
    ): String {
        return ZonedDateTime.parse(utcDate, DateTimeFormatter.ISO_OFFSET_DATE_TIME)
            .withZoneSameInstant(timeZone)
            .format(DateTimeFormatter.ofPattern(pattern))
    }

    fun getCurrentDate(): String {
        return LocalDate.now(ZoneId.systemDefault()).format(DateTimeFormatter.ISO_LOCAL_DATE)
    }

    fun getLocalTimeFromUTCDate(utcDate: String): String {
        return convertUtcDateTimeToLocal(
            utcDate = utcDate,
            timeZone = ZoneId.systemDefault(),
            pattern = TIME_PATTERN,
        )
    }

    fun getDateFromMilliseconds(milliseconds: Long?): String? {
        val instant = milliseconds?.let { Instant.ofEpochMilli(it) } ?: return null
        val date = ZonedDateTime.ofInstant(instant, ZoneId.systemDefault())
        val formatter = DateTimeFormatter.ofPattern(DATE_PATTERN)
        return date.format(formatter)
    }

    fun currentYear() = Year.now(ZoneId.systemDefault()).value
}
