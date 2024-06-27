package com.diegopizzo.design.components.image

import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import com.diegopizzo.design.components.image.PainterViewData.Companion.drawableResourcePainter
import com.diegopizzo.design.theme.Icons
import com.diegopizzo.design.theme.LFTheme
import com.diegopizzo.design.tokens.ContentAlphaTokens
import com.diegopizzo.design.util.conditional

@Composable
fun LFIcon(
    viewData: LFIconViewData,
    modifier: Modifier = Modifier,
) {
    with(viewData) {
        Icon(
            painter = painter(),
            contentDescription = contentDescription,
            modifier = modifier
                .conditional(tint == null) {
                    alpha(if (enabled) ContentAlphaTokens.High else ContentAlphaTokens.Disabled)
                },
            tint = tint ?: Color.Unspecified,
        )
    }
}

@Immutable
data class LFIconViewData(
    val painter: PainterViewData,
    val contentDescription: String? = null,
    val tint: Color? = null,
    val enabled: Boolean = true,
)

@Preview("Default", "LFIcon")
@Composable
fun LFIconPreview(
    @PreviewParameter(LFIconPreviewParameterProvider::class)
    viewData: LFIconViewData,
) {
    LFTheme {
        LFIcon(
            viewData = viewData,
            modifier = Modifier.size(48.dp),
        )
    }
}

private class LFIconPreviewParameterProvider : PreviewParameterProvider<LFIconViewData> {
    override val values: Sequence<LFIconViewData>
        get() = listOf(
            LFIconViewData(
                painter = drawableResourcePainter(Icons.ItalyFlag),
                enabled = true,
            ),
            LFIconViewData(
                painter = drawableResourcePainter(Icons.ItalyFlag),
                enabled = false,
            ),
        ).asSequence()
}
