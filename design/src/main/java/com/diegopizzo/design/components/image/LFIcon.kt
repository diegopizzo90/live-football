package com.diegopizzo.design.components.image

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
import com.diegopizzo.design.R
import com.diegopizzo.design.components.image.PainterViewData.Companion.drawableResourcePainter
import com.diegopizzo.design.theme.LFTheme
import com.diegopizzo.design.tokens.ContentAlphaTokens

@Composable
fun LFIcon(
    viewData: LFIconViewData,
    modifier: Modifier = Modifier,
) {
    with(viewData) {
        Icon(
            painter = painter(),
            contentDescription = contentDescription,
            modifier = modifier.disabled(enabled, tint),
            tint = tint ?: Color.Unspecified,
        )
    }
}

data class LFIconViewData(
    val painter: PainterViewData,
    val contentDescription: String? = null,
    val tint: Color? = null,
    val enabled: Boolean = true,
)

private fun Modifier.disabled(enabled: Boolean, tint: Color? = null): Modifier {
    return if (tint == null) {
        alpha(if (enabled) ContentAlphaTokens.High else ContentAlphaTokens.Disabled)
    } else {
        Modifier
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
                painter = drawableResourcePainter(R.drawable.ic_italy),
                enabled = true,
            ),
            LFIconViewData(
                painter = drawableResourcePainter(R.drawable.ic_italy),
                enabled = false,
            ),
        ).asSequence()
}
