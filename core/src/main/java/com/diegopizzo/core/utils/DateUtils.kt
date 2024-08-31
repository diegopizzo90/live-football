package com.diegopizzo.core.utils

import java.time.ZoneId
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter

object DateUtils {
    private fun convertUtcDateTimeToLocal(
        utcDate: String,
        timeZone: ZoneId,
        pattern: String,
    ): String {
        return ZonedDateTime.parse(utcDate, DateTimeFormatter.ISO_OFFSET_DATE_TIME)
            .withZoneSameInstant(timeZone)
            .format(DateTimeFormatter.ofPattern(pattern))
    }

    fun getLocalTimeFromUTCDate(utcDate: String): String {
        val timePattern = "HH:mm"
        return convertUtcDateTimeToLocal(
            utcDate = utcDate,
            timeZone = ZoneId.systemDefault(),
            pattern = timePattern,
        )
    }
}
