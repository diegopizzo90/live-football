package com.diegopizzo.core.utils

import com.diegopizzo.core.utils.DateUtils.Companion.DEFAULT_DATE_PATTERN
import java.time.Instant
import java.time.LocalDate
import java.time.Year
import java.time.ZoneId
import java.time.ZoneOffset
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import java.time.format.TextStyle
import java.util.Locale

interface DateUtils {

    companion object {
        const val DEFAULT_DATE_PATTERN = "yyyy-MM-dd"
        const val DEFAULT_MONTH_YEAR_PATTERN = "MMMM yyyy"
    }

    data class CalendarDisplayInfo(
        val dayName: String,
        val dayNumber: String,
    )

    /**
     * Generates a list of LocalDate objects starting from the provided startDate.
     * @param startDate String date in the format yyyy-MM-dd.
     * @return List of LocalDate objects.
     */
    fun generateDateList(startDate: String): List<LocalDate>

    /**
     * Gets the current year.
     * @return Current year as an integer.
     */
    fun currentYear(): Int

    /**
     * Converts milliseconds to a formatted date string.
     * @param milliseconds The time in milliseconds.
     * @return The formatted date string or null if milliseconds are invalid.
     */
    fun getDateFromMilliseconds(milliseconds: Long?): String?

    /**
     * Converts UTC date string to local time formatted string.
     * @param utcDate Date in UTC format.
     * @return Formatted local time as string.
     */
    fun getLocalTimeFromUTCDate(utcDate: String): String

    /**
     * Gets the current date as a string.
     * @return Formatted current date string.
     */
    fun getCurrentDate(): String

    /**
     * Formats the provided LocalDate using the default pattern (yyyy-MM-dd).
     * @param date LocalDate object to format.
     * @return Formatted date string.
     */
    fun formatDate(date: LocalDate, pattern: String = DEFAULT_DATE_PATTERN): String

    /**
     * Converts the provided LocalDate to UTC milliseconds.
     * @param date LocalDate object to convert.
     * @return Time in UTC milliseconds.
     */
    fun toUtcMilliseconds(date: LocalDate): Long

    /**
     * Provides display information about the calendar for the provided date.
     * @param date LocalDate object to retrieve display info.
     * @return CalendarDisplayInfo containing day name and day number.
     */
    fun getCalendarDisplayInfo(date: LocalDate): CalendarDisplayInfo

    /**
     * Gets the current Unix timestamp (the number of seconds since January 1, 1970)
     * @return current timestamp.
     */
    fun getCurrentUnixTimestamp(): Long

    /**
     * Check if the provided date is the current date.
     * @param dateString String representation of a date
     * @param format String date format, default: DEFAULT_DATE_PATTERN
     * @return Boolean
     */
    fun isToday(dateString: String, format: String = DEFAULT_DATE_PATTERN): Boolean
}

class DateUtilsImpl(private val zoneId: ZoneId, private val locale: Locale) : DateUtils {

    companion object {
        private const val TIME_PATTERN = "HH:mm"
    }

    private fun convertUtcDateTimeToLocal(
        utcDate: String,
        timeZone: ZoneId,
        pattern: String,
    ): String {
        return ZonedDateTime.parse(utcDate, DateTimeFormatter.ISO_OFFSET_DATE_TIME)
            .withZoneSameInstant(timeZone)
            .format(DateTimeFormatter.ofPattern(pattern))
    }

    override fun getCurrentDate(): String {
        return LocalDate.now(zoneId).format(DateTimeFormatter.ISO_LOCAL_DATE)
    }

    override fun getCurrentUnixTimestamp(): Long {
        return ZonedDateTime.now(zoneId).toInstant().epochSecond
    }

    override fun getLocalTimeFromUTCDate(utcDate: String): String {
        return convertUtcDateTimeToLocal(
            utcDate = utcDate,
            timeZone = zoneId,
            pattern = TIME_PATTERN,
        )
    }

    override fun getDateFromMilliseconds(milliseconds: Long?): String? {
        val instant = milliseconds?.let { Instant.ofEpochMilli(it) } ?: return null
        val date = ZonedDateTime.ofInstant(instant, zoneId)
        val formatter = DateTimeFormatter.ofPattern(DEFAULT_DATE_PATTERN)
        return date.format(formatter)
    }

    override fun currentYear() = Year.now(zoneId).value

    override fun generateDateList(startDate: String): List<LocalDate> {
        val date = LocalDate.parse(startDate, DateTimeFormatter.ofPattern(DEFAULT_DATE_PATTERN))

        return (-3..3).map { date.plusDays(it.toLong()) }
    }

    override fun formatDate(date: LocalDate, pattern: String): String {
        return date.format(DateTimeFormatter.ofPattern(pattern))
    }

    override fun getCalendarDisplayInfo(date: LocalDate): DateUtils.CalendarDisplayInfo {
        return DateUtils.CalendarDisplayInfo(
            dayName = date.dayOfWeek.getDisplayName(TextStyle.SHORT, locale),
            dayNumber = date.dayOfMonth.toString(),
        )
    }

    override fun toUtcMilliseconds(date: LocalDate): Long {
        return date.atStartOfDay().toInstant(ZoneOffset.UTC).toEpochMilli()
    }

    override fun isToday(dateString: String, format: String): Boolean {
        val formatter = DateTimeFormatter.ofPattern(format)
        val date = LocalDate.parse(dateString, formatter)
        return date.isEqual(LocalDate.now(zoneId))
    }
}
