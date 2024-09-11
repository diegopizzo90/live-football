@file:OptIn(ExperimentalMaterial3Api::class)

package com.diegopizzo.design.components.datepicker

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerColors
import androidx.compose.material3.DatePickerDefaults
import androidx.compose.material3.DatePickerFormatter
import androidx.compose.material3.DatePickerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SelectableDates
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import com.diegopizzo.core.utils.DateUtils
import com.diegopizzo.design.theme.LFTheme
import com.diegopizzo.design.tokens.ShapeTokens
import com.diegopizzo.design.tokens.SpaceTokens

@Composable
fun LFCalendar(
    modifier: Modifier = Modifier,
    state: DatePickerState = rememberLFCalendarState(),
    onDateSelected: (String?) -> Unit = {},
) {
    var dateSelected by remember { mutableStateOf(state.selectedDateMillis) }

    DatePicker(
        modifier = modifier
            .padding(horizontal = SpaceTokens.MediumLarge)
            .clip(LFCalendarDefaults.LFCalendarContainerShape)
            .background(MaterialTheme.colorScheme.surfaceContainer),
        state = state,
        dateFormatter = remember { LFCalendarDefaults.dateFormatter() },
        showModeToggle = false,
        title = null,
        headline = null,
        colors = LFCalendarDefaults.colors(),
    )
    if (dateSelected != state.selectedDateMillis) {
        dateSelected = state.selectedDateMillis
        onDateSelected(DateUtils.getDateFromMilliseconds(dateSelected))
    }
}

@ExperimentalMaterial3Api
@Stable
internal object LFCalendarDefaults {

    val LFCalendarContainerShape = ShapeTokens.CornerLarge

    @Composable
    fun colors() = MaterialTheme.colorScheme.defaultDatePickerColors

    private val ColorScheme.defaultDatePickerColors: DatePickerColors
        @Composable
        get() {
            return DatePickerDefaults.colors(
                containerColor = MaterialTheme.colorScheme.surfaceContainer,
                navigationContentColor = MaterialTheme.colorScheme.onBackground,
                yearContentColor = MaterialTheme.colorScheme.onBackground,
                dayContentColor = MaterialTheme.colorScheme.inverseSurface,
                todayContentColor = MaterialTheme.colorScheme.inverseSurface,
                todayDateBorderColor = MaterialTheme.colorScheme.primary,
                selectedDayContentColor = MaterialTheme.colorScheme.onPrimary,
                selectedDayContainerColor = MaterialTheme.colorScheme.primary,
            )
        }

    fun dateFormatter(): DatePickerFormatter = DatePickerDefaults.dateFormatter(
        yearSelectionSkeleton = DateUtils.MONTH_YEAR_PATTERN,
        selectedDateSkeleton = DateUtils.DATE_PATTERN,
    )
}

@Composable
@ExperimentalMaterial3Api
fun rememberLFCalendarState(initialSelectedDateMillis: Long? = null): DatePickerState {
    val currentYear = DateUtils.currentYear()
    val selectableDates: SelectableDates = object : SelectableDates {
        override fun isSelectableYear(year: Int): Boolean {
            return year == currentYear
        }
    }

    return rememberDatePickerState(
        initialSelectedDateMillis = initialSelectedDateMillis,
        yearRange = (currentYear - 1)..(currentYear + 1),
        selectableDates = selectableDates,
    )
}

@Preview("Default", "LFCalendar")
@Preview("Dark theme", "LFCalendar", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun LFCalendarPreview() {
    LFTheme {
        LFCalendar()
    }
}
