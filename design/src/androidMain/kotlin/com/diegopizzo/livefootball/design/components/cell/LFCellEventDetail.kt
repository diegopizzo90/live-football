package com.diegopizzo.livefootball.design.components.cell

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.heightIn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import com.diegopizzo.livefootball.design.components.divider.LFHorizontalSpacer
import com.diegopizzo.livefootball.design.components.image.LFIcon
import com.diegopizzo.livefootball.design.components.image.LFIconViewData
import com.diegopizzo.livefootball.design.components.image.PainterResource
import com.diegopizzo.livefootball.design.components.text.LFTitleSmall
import com.diegopizzo.livefootball.design.theme.Icons
import com.diegopizzo.livefootball.design.theme.LFTheme
import com.diegopizzo.livefootball.design.tokens.SpaceTokens

@Composable
fun LFCellEventDetail(
    viewData: LFCellEventDetailViewData,
    modifier: Modifier = Modifier,
) {
    with(viewData) {
        Row(
            modifier = modifier.heightIn(min = 24.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            LFHorizontalSpacer(width = SpaceTokens.Mini)
            LFTitleSmall(text = time)
            LFHorizontalSpacer(width = SpaceTokens.Large)
            if (isLeftAligned) {
                ContentLeftAligned(viewData = viewData)
            } else {
                ContentRightAligned(viewData = viewData)
            }
        }
    }
}

@Composable
private fun ContentRightAligned(
    viewData: LFCellEventDetailViewData,
) {
    with(viewData) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.End,
        ) {
            Spacer(modifier = Modifier.weight(1f))
            score?.let { LFTitleSmall(text = it) }
            LFHorizontalSpacer(width = SpaceTokens.Large)
            LFIcon(viewData = icon)
            LFHorizontalSpacer(width = SpaceTokens.Mini)
            LFTitleSmall(text = name)
            LFHorizontalSpacer(width = SpaceTokens.Mini)
        }
    }
}

@Composable
private fun ContentLeftAligned(
    viewData: LFCellEventDetailViewData,
) {
    with(viewData) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
        ) {
            LFTitleSmall(text = name)
            LFHorizontalSpacer(width = SpaceTokens.Mini)
            LFIcon(viewData = icon)
            LFHorizontalSpacer(width = SpaceTokens.Large)
            score?.let { LFTitleSmall(text = it) }
            Spacer(modifier = Modifier.weight(1f))
        }
    }
}

@Preview("Default", "LFCellEventDetail", showBackground = true)
@Preview("Dark theme", "LFCellEventDetail", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun LFCellEventDetailPreview(
    @PreviewParameter(LFCellEventDetailPreviewParameterProvider::class)
    viewData: LFCellEventDetailViewData,
) {
    LFTheme {
        LFCellEventDetail(
            viewData = viewData,
        )
    }
}

private class LFCellEventDetailPreviewParameterProvider : PreviewParameterProvider<LFCellEventDetailViewData> {
    private val default
        get() = LFCellEventDetailViewData(
            time = "27'",
            name = "P. Aubameyang",
            icon = LFIconViewData(
                painter = PainterResource.DrawableResource(Icons.YellowCard.idLightTheme),
            ),
        )
    override val values: Sequence<LFCellEventDetailViewData>
        get() = listOf(
            default,
            default.copy(
                icon = LFIconViewData(
                    painter = PainterResource.DrawableResource(Icons.Goal.idLightTheme),
                ),
                score = "1 - 0",
            ),
            default.copy(
                icon = LFIconViewData(
                    painter = PainterResource.DrawableResource(Icons.Goal.idLightTheme),
                ),
                score = "0 - 1",
                isLeftAligned = false,
            ),
        ).asSequence()
}
