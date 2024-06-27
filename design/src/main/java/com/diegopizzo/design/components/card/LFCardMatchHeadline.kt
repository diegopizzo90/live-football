package com.diegopizzo.design.components.card

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.diegopizzo.design.components.cell.LFCellHeadline
import com.diegopizzo.design.components.cell.LFCellHeadlineViewData
import com.diegopizzo.design.components.cell.LFCellIconViewData
import com.diegopizzo.design.components.cell.LFCellMatch
import com.diegopizzo.design.components.cell.LFCellMatchViewData
import com.diegopizzo.design.components.cell.LFCellResultViewData
import com.diegopizzo.design.components.divider.LFHorizontalDivider
import com.diegopizzo.design.components.image.LFIconViewData
import com.diegopizzo.design.components.image.PainterViewData.Companion.drawableResourcePainter
import com.diegopizzo.design.theme.Icons
import com.diegopizzo.design.theme.LFTheme
import com.diegopizzo.design.tokens.SpaceTokens

@Composable
fun LFCardMatchHeadline(
    viewData: LFCardMatchHeadlineViewData,
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {},
) {
    with(viewData) {
        LFCard(
            modifier = modifier,
            onClick = onClick,
        ) {
            Column {
                LFCellHeadline(
                    viewData = cellHeadlineViewData,
                )
                LFHorizontalDivider()
                matches.forEachIndexed { index, match ->
                    LFCellMatch(
                        viewData = match,
                    )
                    if (index < matches.lastIndex) LFHorizontalDivider()
                }
            }
        }
    }
}

@Immutable
data class LFCardMatchHeadlineViewData(
    val cellHeadlineViewData: LFCellHeadlineViewData,
    val matches: List<LFCellMatchViewData>,
)

private class LFCardMatchHeadlinePreviewParameterProvider : PreviewParameterProvider<LFCardMatchHeadlineViewData> {
    override val values: Sequence<LFCardMatchHeadlineViewData>
        get() = listOf(
            defaultPreviewValue,
        ).asSequence()
}

@Preview("Default", "LFCardMatchHeadline")
@Preview("Dark theme", "LFCardMatchHeadline", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun LFCardMatchHeadlinePreview(
    @PreviewParameter(LFCardMatchHeadlinePreviewParameterProvider::class)
    viewData: LFCardMatchHeadlineViewData,
) {
    LFTheme {
        Box(
            modifier = Modifier
                .background(MaterialTheme.colorScheme.background)
                .padding(SpaceTokens.ExtraLarge),
            contentAlignment = Alignment.Center,
        ) {
            LFCardMatchHeadline(
                viewData = viewData,
            )
        }
    }
}

private val defaultPreviewValue
    get() = LFCardMatchHeadlineViewData(
        cellHeadlineViewData = LFCellHeadlineViewData(
            icon = LFIconViewData(drawableResourcePainter(Icons.ItalyFlag)),
            headlineText = "Euro Cup 2024",
            subtitle = "Group Stage",
        ),
        matches = listOf(
            LFCellMatchViewData(
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
            ),
            LFCellMatchViewData(
                cellIconHome = LFCellIconViewData(
                    icon = LFIconViewData(
                        painter = drawableResourcePainter(Icons.ItalyFlag),
                    ),
                    text = "Home Home",
                ),
                cellIconAway = LFCellIconViewData(
                    icon = LFIconViewData(
                        painter = drawableResourcePainter(Icons.ItalyFlag),
                    ),
                    text = "Away",
                ),
                result = LFCellResultViewData(resultHome = "1", resultAway = "2"),
                time = "27'",
            ),
        ),
    )
