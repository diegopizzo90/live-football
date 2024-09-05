package com.diegopizzo.design.components.card

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.diegopizzo.design.components.cell.LFCellIconViewData
import com.diegopizzo.design.components.cell.LFCellMatch
import com.diegopizzo.design.components.cell.LFCellMatchViewData
import com.diegopizzo.design.components.cell.LFCellResultViewData
import com.diegopizzo.design.components.image.LFIconViewData
import com.diegopizzo.design.components.image.PainterViewData.Companion.drawableResourcePainter
import com.diegopizzo.design.theme.Icons
import com.diegopizzo.design.theme.LFTheme
import com.diegopizzo.design.tokens.SpaceTokens

@Composable
fun LFCardMatch(
    viewData: LFCardMatchViewData,
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {},
) {
    LFCard(
        modifier = modifier,
        onClick = onClick,
    ) {
        LFCellMatch(
            viewData = viewData.match,
        )
    }
}

@Immutable
data class LFCardMatchViewData(
    val match: LFCellMatchViewData,
    val isLiveMatch: Boolean = false,
)

private class LFCardMatchPreviewParameterProvider : PreviewParameterProvider<LFCardMatchViewData> {
    override val values: Sequence<LFCardMatchViewData>
        get() = listOf(
            defaultPreviewValue,
            defaultPreviewValue.copy(
                match = defaultPreviewValue.match.copy(
                    time = "27'",
                    result = LFCellResultViewData(
                        resultHome = "1",
                        resultAway = "2",
                    ),
                ),
            ),
        ).asSequence()
}

@Preview("Default", "LFCardMatch")
@Preview("Dark theme", "LFCardMatch", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun LFCardMatchPreview(
    @PreviewParameter(LFCardMatchPreviewParameterProvider::class)
    viewData: LFCardMatchViewData,
) {
    LFTheme {
        Box(
            modifier = Modifier
                .background(MaterialTheme.colorScheme.background)
                .padding(SpaceTokens.MediumLarge),
        ) {
            LFCardMatch(
                viewData = viewData,
            )
        }
    }
}

private val defaultPreviewValue
    get() = LFCardMatchViewData(
        match = LFCellMatchViewData(
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
    )
