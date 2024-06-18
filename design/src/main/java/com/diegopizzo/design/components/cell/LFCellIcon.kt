package com.diegopizzo.design.components.cell

import android.content.res.Configuration
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
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
import com.diegopizzo.design.components.image.PainterViewData
import com.diegopizzo.design.components.text.LFHeadlineMedium
import com.diegopizzo.design.theme.Icons
import com.diegopizzo.design.theme.LFTheme
import com.diegopizzo.design.tokens.SpaceTokens

@Composable
fun LFCellIcon(
    viewData: LFCellIconViewData,
    modifier: Modifier = Modifier,
) {
    with(viewData) {
        Row(
            modifier = modifier,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            LFIcon(
                viewData = icon.copy(enabled = enabled),
                modifier = Modifier.size(32.dp),
            )
            LFHorizontalSpacer(width = SpaceTokens.Large)
            LFHeadlineMedium(
                text = text,
                maxLines = 1,
                enabled = enabled,
            )
        }
    }
}

@Immutable
data class LFCellIconViewData(
    val icon: LFIconViewData,
    val text: String,
    val enabled: Boolean = true,
)

@Preview("Default", "LFCellIcon", showBackground = true)
@Preview("Dark theme", "LFCellIcon", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun LFCellIconPreview(
    @PreviewParameter(LFCellIconPreviewParameterProvider::class)
    viewData: LFCellIconViewData,
) {
    LFTheme {
        LFCellIcon(
            viewData = viewData,
        )
    }
}

private class LFCellIconPreviewParameterProvider : PreviewParameterProvider<LFCellIconViewData> {
    private val default
        get() = LFCellIconViewData(
            icon = LFIconViewData(PainterViewData.drawableResourcePainter(Icons.ItalyFlag)),
            text = "LFCellIcon",
        )

    override val values: Sequence<LFCellIconViewData>
        get() = listOf(
            default,
            default.copy(enabled = false),
        ).asSequence()
}
