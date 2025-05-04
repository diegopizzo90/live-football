package com.diegopizzo.livefootball.design.components.image

import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import com.diegopizzo.livefootball.design.theme.Icons
import com.diegopizzo.livefootball.design.theme.LFTheme
import com.diegopizzo.livefootball.design.tokens.ContentAlphaTokens
import com.diegopizzo.livefootball.design.util.conditional

@Composable
fun LFIcon(
    viewData: LFIconViewData,
    modifier: Modifier = Modifier,
) {
    with(viewData) {
        Icon(
            painter = painter.toPainter(),
            contentDescription = contentDescription,
            modifier = modifier
                .conditional(tintHex == null) {
                    alpha(if (enabled) ContentAlphaTokens.High else ContentAlphaTokens.Disabled)
                },
            tint = tintHex?.toComposableColor() ?: Color.Unspecified,
        )
    }
}

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
                painter = PainterResource.DrawableResource(Icons.ItalyFlag.idLightTheme),
                enabled = true,
            ),
            LFIconViewData(
                painter = PainterResource.DrawableResource(Icons.ItalyFlag.idLightTheme),
                enabled = false,
            ),
        ).asSequence()
}
