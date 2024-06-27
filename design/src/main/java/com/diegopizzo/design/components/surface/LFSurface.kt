package com.diegopizzo.design.components.surface

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.Dp
import com.diegopizzo.design.components.text.LFBodyMedium
import com.diegopizzo.design.theme.ColorPalette
import com.diegopizzo.design.theme.LFTheme
import com.diegopizzo.design.tokens.BorderStrokeTokens
import com.diegopizzo.design.tokens.ElevationTokens
import com.diegopizzo.design.tokens.ShapeTokens
import com.diegopizzo.design.tokens.SpaceTokens

@Composable
fun LFSurface(
    modifier: Modifier = Modifier,
    onClick: (() -> Unit)? = null,
    color: Color = MaterialTheme.colorScheme.surface,
    contentColor: Color = contentColorFor(color),
    shadowElevation: LFSurfaceShadowElevation = LFSurfaceShadowElevation.Zero,
    shape: LFSurfaceShape = LFSurfaceShape.Zero,
    border: BorderStrokeTokens? = null,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    content: @Composable () -> Unit,
) {
    if (onClick != null) {
        Surface(
            onClick = onClick,
            modifier = modifier,
            shape = shape.value,
            color = color,
            contentColor = contentColor,
            shadowElevation = shadowElevation.value,
            border = border?.borderStroke,
            interactionSource = interactionSource,
        ) {
            content()
        }
    } else {
        Surface(
            modifier = modifier,
            shape = shape.value,
            color = color,
            contentColor = contentColor,
            shadowElevation = shadowElevation.value,
            border = border?.borderStroke,
        ) {
            content()
        }
    }
}

enum class LFSurfaceShadowElevation(val value: Dp) {
    Zero(ElevationTokens.Level0),
    Mini(ElevationTokens.Level2),
    Small(ElevationTokens.Level4),
    Medium(ElevationTokens.Level6),
    Large(ElevationTokens.Level8),
}

enum class LFSurfaceShape(val value: Shape) {
    Zero(RectangleShape),
    Mini(ShapeTokens.CornerMini),
    Small(ShapeTokens.CornerSmall),
    Medium(ShapeTokens.CornerMedium),
    Large(ShapeTokens.CornerLarge),
    Big(ShapeTokens.CornerBig),
}

@Preview("Default", "LFSurface")
@Preview("Dark theme", "LFSurface", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun LFSurfacePreview(
    @PreviewParameter(LFSurfacePreviewParameterProvider::class)
    viewData: LFSurfaceViewData,
) {
    LFTheme {
        Box(
            modifier = Modifier
                .background(MaterialTheme.colorScheme.background)
                .padding(SpaceTokens.Large),
        ) {
            LFSurface(
                shadowElevation = viewData.shadowElevation,
                shape = viewData.shape,
                border = viewData.border,
            ) {
                LFBodyMedium(
                    text = "LFSurface",
                    modifier = Modifier.padding(SpaceTokens.Medium),
                )
            }
        }
    }
}

@Immutable
private data class LFSurfaceViewData(
    val shadowElevation: LFSurfaceShadowElevation,
    val shape: LFSurfaceShape,
    val border: BorderStrokeTokens? = null,
)

private class LFSurfacePreviewParameterProvider : PreviewParameterProvider<LFSurfaceViewData> {
    private val defaultValue
        get() = LFSurfaceViewData(
            shadowElevation = LFSurfaceShadowElevation.Zero,
            shape = LFSurfaceShape.Zero,
            border = null,
        )

    override val values: Sequence<LFSurfaceViewData>
        get() = listOf(
            defaultValue,
            defaultValue.copy(shape = LFSurfaceShape.Small),
            defaultValue.copy(shape = LFSurfaceShape.Medium),
            defaultValue.copy(shape = LFSurfaceShape.Large),
            defaultValue.copy(shape = LFSurfaceShape.Big),
            defaultValue.copy(shadowElevation = LFSurfaceShadowElevation.Mini),
            defaultValue.copy(shadowElevation = LFSurfaceShadowElevation.Small),
            defaultValue.copy(shadowElevation = LFSurfaceShadowElevation.Medium),
            defaultValue.copy(shadowElevation = LFSurfaceShadowElevation.Large),
            defaultValue.copy(border = BorderStrokeTokens.Small(ColorPalette.Primary)),
            defaultValue.copy(border = BorderStrokeTokens.Medium(ColorPalette.Primary)),
            defaultValue.copy(border = BorderStrokeTokens.Thick(ColorPalette.Primary)),
        ).asSequence()
}
