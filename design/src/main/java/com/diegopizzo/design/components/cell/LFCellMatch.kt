package com.diegopizzo.design.components.cell

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import com.diegopizzo.design.components.custom.LFPulsatingUnderline
import com.diegopizzo.design.components.divider.LFHorizontalSpacer
import com.diegopizzo.design.components.divider.LFVerticalSpacer
import com.diegopizzo.design.components.image.LFIconViewData
import com.diegopizzo.design.components.image.PainterViewData.Companion.drawableResourcePainter
import com.diegopizzo.design.components.text.LFHeadlineMedium
import com.diegopizzo.design.components.text.LFHeadlineSmall
import com.diegopizzo.design.theme.Icons
import com.diegopizzo.design.theme.LFTheme
import com.diegopizzo.design.tokens.SpaceTokens
import com.diegopizzo.design.util.measureTextWidth

@Composable
fun LFCellMatch(
    viewData: LFCellMatchViewData,
    modifier: Modifier = Modifier,
) {
    with(viewData) {
        Row(
            modifier = modifier
                .heightIn(116.dp)
                .padding(SpaceTokens.MediumLarge),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            LFPulsatingUnderline(
                targetValue = if (isLiveMatch) measureTextWidth(time).value else 0f,
            ) {
                LFHeadlineSmall(
                    text = time,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.defaultMinSize(minWidth = 48.dp),
                )
            }
            LFHorizontalSpacer(width = SpaceTokens.Large)
            Column(
                modifier = Modifier.weight(1f),
            ) {
                LFCellIcon(viewData = cellIconHome)
                LFVerticalSpacer(height = SpaceTokens.Large)
                LFCellIcon(viewData = cellIconAway)
            }
            Column(
                modifier = Modifier.weight(.1f),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                result?.let {
                    LFHeadlineMedium(text = it.resultHome)
                    LFVerticalSpacer(height = SpaceTokens.ExtraLarge)
                    LFHeadlineMedium(text = it.resultAway)
                }
            }
        }
    }
}

@Immutable
data class LFCellMatchViewData(
    val id: Long = 0,
    val leagueId: Long = 0,
    val cellIconHome: LFCellIconViewData,
    val cellIconAway: LFCellIconViewData,
    val result: LFCellResultViewData? = null,
    val time: String,
    val isLiveMatch: Boolean = false,
)

data class LFCellResultViewData(
    val resultHome: String,
    val resultAway: String,
)

@Preview("Default", "LFCellMatch", showBackground = true)
@Preview("Dark theme", "LFCellMatch", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun LFCellMatchPreview(
    @PreviewParameter(LFCellMatchPreviewParameterProvider::class)
    viewData: LFCellMatchViewData,
) {
    LFTheme {
        LFCellMatch(
            viewData = viewData,
        )
    }
}

private class LFCellMatchPreviewParameterProvider : PreviewParameterProvider<LFCellMatchViewData> {
    private val default
        get() = LFCellMatchViewData(
            cellIconHome = LFCellIconViewData(
                icon = LFIconViewData(
                    painter = drawableResourcePainter(Icons.ItalyFlag),
                ),
                text = "HomeHome Home",
            ),
            cellIconAway = LFCellIconViewData(
                icon = LFIconViewData(
                    painter = drawableResourcePainter(Icons.ItalyFlag),
                ),
                text = "Away",
            ),
            time = "10:30",
        )

    override val values: Sequence<LFCellMatchViewData>
        get() = listOf(
            default,
            default.copy(time = "23'", result = LFCellResultViewData("1", "2")),
        ).asSequence()
}
