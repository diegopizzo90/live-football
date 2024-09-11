package com.diegopizzo.design.components.datepicker

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.diegopizzo.design.components.divider.LFHorizontalSpacer
import com.diegopizzo.design.components.divider.LFVerticalSpacer
import com.diegopizzo.design.components.image.LFIcon
import com.diegopizzo.design.components.image.LFIconViewData
import com.diegopizzo.design.components.image.PainterViewData
import com.diegopizzo.design.components.text.LFBodySmall
import com.diegopizzo.design.components.text.LFLabelLarge
import com.diegopizzo.design.theme.Icons
import com.diegopizzo.design.theme.LFTheme
import com.diegopizzo.design.tokens.ShapeTokens
import com.diegopizzo.design.tokens.SpaceTokens
import com.diegopizzo.design.util.conditional

private const val MAX_NUMBER_OF_DAYS_DISPLAYED = 7

@Composable
fun LFDatePicker(
    viewData: List<LFDatePickerViewData>,
    modifier: Modifier = Modifier,
    onClick: (date: String) -> Unit = {},
    onCalendarIconClick: () -> Unit = {},
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.background)
            .padding(horizontal = SpaceTokens.ExtraLarge),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,
    ) {
        viewData.take(MAX_NUMBER_OF_DAYS_DISPLAYED).forEach { chip ->
            LFDayPicker(
                viewData = chip,
                onClick = onClick,
            )
            LFHorizontalSpacer(width = SpaceTokens.Small)
        }
        LFIcon(
            viewData = LFIconViewData(
                PainterViewData.drawableResourcePainter(Icons.Calendar),
            ),
            modifier = Modifier
                .clickable {
                    onCalendarIconClick()
                },
        )
    }
}

@Composable
private fun RowScope.LFDayPicker(
    viewData: LFDatePickerViewData,
    modifier: Modifier = Modifier,
    onClick: (date: String) -> Unit = {},
) {
    with(viewData) {
        Column(
            modifier = modifier
                .weight(1f)
                .conditional(selected) {
                    clip(ShapeTokens.CornerSmall)
                        .background(MaterialTheme.colorScheme.primary)
                }
                .clickable { onClick(viewData.fullDate) },
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {
            LFVerticalSpacer(height = SpaceTokens.Small)
            LFBodySmall(
                text = dayName,
                color = if (selected) MaterialTheme.colorScheme.onSecondary else Color.Unspecified,
            )
            LFVerticalSpacer(height = SpaceTokens.Mini)
            LFLabelLarge(
                text = dayNumber,
                color = if (selected) MaterialTheme.colorScheme.onSecondary else Color.Unspecified,
            )
            LFVerticalSpacer(height = SpaceTokens.Small)
        }
    }
}

@Immutable
data class LFDatePickerViewData(
    val dayName: String,
    val dayNumber: String,
    val fullDate: String,
    val millis: Long? = null,
    val selected: Boolean = false,
)

private class LFDatePickerPreviewParameterProvider : PreviewParameterProvider<List<LFDatePickerViewData>> {
    private val default
        get() = LFDatePickerViewData(
            dayName = "Mon",
            dayNumber = "1",
            fullDate = "2024-01-01",
        )

    override val values: Sequence<List<LFDatePickerViewData>>
        get() = listOf(
            listOf(
                default,
                default.copy(dayName = "Tue", dayNumber = "2"),
                default.copy(dayName = "Wed", dayNumber = "3"),
                default.copy(dayName = "Thu", dayNumber = "4", selected = true),
                default.copy(dayName = "Fri", dayNumber = "5"),
                default.copy(dayName = "Sat", dayNumber = "6"),
                default.copy(dayName = "Sun", dayNumber = "7"),
            ),
        ).asSequence()
}

@Preview("Default", "LFDatePicker")
@Preview("Dark theme", "LFDatePicker", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun LFDatePickerPreview(
    @PreviewParameter(LFDatePickerPreviewParameterProvider::class)
    viewData: List<LFDatePickerViewData>,
) {
    LFTheme {
        LFDatePicker(
            viewData = viewData,
        )
    }
}
