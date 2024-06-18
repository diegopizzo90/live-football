package com.diegopizzo.design.components.cell

import android.content.res.Configuration
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import com.diegopizzo.design.components.divider.LFHorizontalSpacer
import com.diegopizzo.design.components.image.LFIcon
import com.diegopizzo.design.components.image.LFIconViewData
import com.diegopizzo.design.components.image.PainterViewData.Companion.drawableResourcePainter
import com.diegopizzo.design.components.text.LFBodyMedium
import com.diegopizzo.design.components.text.LFHeadlineMedium
import com.diegopizzo.design.theme.Icons
import com.diegopizzo.design.theme.LFTheme
import com.diegopizzo.design.tokens.SpaceTokens

@Composable
fun LFCellHeadline(
    viewData: LFCellHeadlineViewData,
    modifier: Modifier = Modifier,
) {
    with(viewData) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = modifier
                .heightIn(min = 56.dp)
                .padding(all = SpaceTokens.MediumLarge),
        ) {
            LFIcon(viewData = icon)
            LFHorizontalSpacer(width = SpaceTokens.ExtraLarge)
            Column(
                modifier = Modifier.weight(1f),
            ) {
                LFHeadlineMedium(text = headlineText)
                LFHorizontalSpacer(width = SpaceTokens.Mini)
                LFBodyMedium(text = subtitle)
            }
        }
    }
}

@Immutable
data class LFCellHeadlineViewData(
    val icon: LFIconViewData,
    val headlineText: String,
    val subtitle: String,
)

@Preview("Default", "LFCellHeadline", showBackground = true)
@Preview("Dark theme", "LFCellHeadline", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun LFCellHeadlinePreview(
    @PreviewParameter(LFCellHeadlinePreviewParameterProvider::class)
    viewData: LFCellHeadlineViewData,
) {
    LFTheme {
        LFCellHeadline(
            viewData = viewData,
        )
    }
}

private class LFCellHeadlinePreviewParameterProvider : PreviewParameterProvider<LFCellHeadlineViewData> {
    override val values: Sequence<LFCellHeadlineViewData>
        get() = listOf(
            LFCellHeadlineViewData(
                icon = LFIconViewData(drawableResourcePainter(Icons.ItalyFlag)),
                headlineText = "Euro Cup 2024",
                subtitle = "Group Stage",
            ),
        ).asSequence()
}
