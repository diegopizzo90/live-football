package com.diegopizzo.livefootball.design.components.card

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
import com.diegopizzo.livefootball.design.components.cell.LFCellIconViewData
import com.diegopizzo.livefootball.design.components.cell.LFCellMatch
import com.diegopizzo.livefootball.design.components.cell.LFCellMatchViewData
import com.diegopizzo.livefootball.design.components.cell.LFCellResultViewData
import com.diegopizzo.livefootball.design.components.image.LFIconViewData
import com.diegopizzo.livefootball.design.components.image.PainterResource
import com.diegopizzo.livefootball.design.theme.Icons
import com.diegopizzo.livefootball.design.theme.LFTheme
import com.diegopizzo.livefootball.design.tokens.SpaceTokens

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
                    painter = PainterResource.DrawableResource(Icons.ItalyFlag.idLightTheme),
                ),
                text = "HomeHome Home",
            ),
            cellIconAway = LFCellIconViewData(
                icon = LFIconViewData(
                    painter = PainterResource.DrawableResource(Icons.ItalyFlag.idLightTheme),
                ),
                text = "Away",
            ),
            time = "10:30",
        ),
    )
