package com.diegopizzo.livefootball.core.utils

import kotlinx.datetime.Clock
import kotlinx.datetime.DateTimeUnit
import kotlinx.datetime.Instant
import kotlinx.datetime.LocalDate
import kotlinx.datetime.TimeZone
import kotlinx.datetime.atStartOfDayIn
import kotlinx.datetime.plus
import kotlinx.datetime.toLocalDateTime
import kotlinx.datetime.todayIn

interface DateUtils {

    companion object {
        const val DEFAULT_DATE_PATTERN = "yyyy-MM-dd"
        const val DEFAULT_MONTH_YEAR_PATTERN = "MMMM yyyy"
        const val TIME_PATTERN = "HH:mm"
    }

//    data class CalendarDisplayInfo(
//        val dayName: String,
//        val dayNumber: String,
//    )

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

//    /**
//     * Provides display information about the calendar for the provided date.
//     * @param date LocalDate object to retrieve display info.
//     * @return CalendarDisplayInfo containing day name and day number.
//     */
//    fun getCalendarDisplayInfo(date: LocalDate): CalendarDisplayInfo

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

    /**
     * Check if the provided date is in the past.
     * @param dateString String representation of a date
     * @param format String date format, default: DEFAULT_DATE_PATTERN
     * @return Boolean
     */
    fun isInThePast(dateString: String, format: String = DEFAULT_DATE_PATTERN): Boolean
}

class DateUtilsImpl(
    private val timeZone: TimeZone = TimeZone.currentSystemDefault(),
) : DateUtils {

    override fun getCurrentDate(): String =
        Clock.System.todayIn(timeZone).toString()

    override fun getCurrentUnixTimestamp(): Long =
        Clock.System.now().epochSeconds

    override fun getLocalTimeFromUTCDate(utcDate: String): String {
        val instant = Instant.parse(utcDate)
        return instant.toLocalDateTime(timeZone).time.toString()
    }

    override fun getDateFromMilliseconds(milliseconds: Long?): String? =
        milliseconds?.let {
            Instant.fromEpochMilliseconds(it).toLocalDateTime(timeZone).date.toString()
        }

    override fun currentYear(): Int =
        Clock.System.todayIn(timeZone).year

    override fun generateDateList(startDate: String): List<LocalDate> {
        val date = LocalDate.parse(startDate)
        return (-3..3).map { date.plus(it, DateTimeUnit.DAY) }
    }

    override fun formatDate(date: LocalDate, pattern: String): String =
        date.toString()

//    override fun getCalendarDisplayInfo(date: LocalDate): DateUtils.CalendarDisplayInfo {
//        val dayName = date.dayOfWeek.name.lowercase().replaceFirstChar(Char::uppercase)
//        return DateUtils.CalendarDisplayInfo(
//            dayName = dayName,
//            dayNumber = date.dayOfMonth.toString(),
//        )
//    }

    override fun toUtcMilliseconds(date: LocalDate): Long =
        date.atStartOfDayIn(TimeZone.UTC).toEpochMilliseconds()

    override fun isToday(dateString: String, format: String): Boolean {
        val date = LocalDate.parse(dateString)
        return date == Clock.System.todayIn(timeZone)
    }

    override fun isInThePast(dateString: String, format: String): Boolean {
        val date = LocalDate.parse(dateString)
        return date < Clock.System.todayIn(timeZone)
    }
}
