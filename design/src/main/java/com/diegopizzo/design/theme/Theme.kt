package com.diegopizzo.design.theme

import android.content.res.Configuration
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.luminance
import androidx.compose.ui.tooling.preview.Preview
import com.diegopizzo.design.tokens.SpaceTokens

@Composable
fun LFTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit,
) {
    val colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme
    MaterialTheme(
        colorScheme = colorScheme.animateSwitchColorScheme(),
        shapes = Shapes,
        typography = Typography,
    ) {
        content()
    }
}

@Composable
fun ColorScheme.animateSwitchColorScheme() = copy(
    primary = animateColor(primary),
    onPrimary = animateColor(onPrimary),
    primaryContainer = animateColor(primaryContainer),
    onPrimaryContainer = animateColor(onPrimaryContainer),
    secondary = animateColor(secondary),
    onSecondary = animateColor(onSecondary),
    secondaryContainer = animateColor(secondaryContainer),
    onSecondaryContainer = animateColor(onSecondaryContainer),
    tertiary = animateColor(tertiary),
    onTertiary = animateColor(onTertiary),
    tertiaryContainer = animateColor(tertiaryContainer),
    onTertiaryContainer = animateColor(onTertiaryContainer),
    background = animateColor(background),
    onBackground = animateColor(onBackground),
    surface = animateColor(surface),
    onSurface = animateColor(onSurface),
    surfaceVariant = animateColor(surfaceVariant),
    onSurfaceVariant = animateColor(onSurfaceVariant),
    error = animateColor(error),
    onError = animateColor(onError),
    errorContainer = animateColor(errorContainer),
    onErrorContainer = animateColor(onErrorContainer),
    outline = animateColor(outline),
    inverseOnSurface = animateColor(inverseOnSurface),
    inverseSurface = animateColor(inverseSurface),
    inversePrimary = animateColor(inversePrimary),
    surfaceTint = animateColor(surfaceTint),
    outlineVariant = animateColor(outlineVariant),
    scrim = animateColor(scrim),
)

@Composable
private fun animateColor(targetValue: Color) =
    animateColorAsState(
        targetValue = targetValue,
        animationSpec = tween(durationMillis = 750),
        label = "ThemeColorAnimation",
    ).value

@Composable
private fun ColorName(color: Color, colorName: String) {
    val colorCode = "[${color.toHex()}]"
    val textColor = if (color.luminance() > 0.5f) ColorPalette.Black else ColorPalette.White
    Text(
        text = "$colorCode $colorName",
        color = textColor,
        modifier = Modifier
            .background(color)
            .fillMaxWidth()
            .padding(all = SpaceTokens.Large),
    )
}

@Preview("Default", uiMode = Configuration.UI_MODE_NIGHT_NO, heightDp = 1500)
@Preview("Dark Theme", uiMode = Configuration.UI_MODE_NIGHT_YES, heightDp = 1500)
@Composable
private fun PreviewColors() {
    LFTheme {
        Column {
            ColorName(MaterialTheme.colorScheme.primary, "primary")
            ColorName(MaterialTheme.colorScheme.onPrimary, "onPrimary")
            ColorName(MaterialTheme.colorScheme.primaryContainer, "primaryContainer")
            ColorName(MaterialTheme.colorScheme.onPrimaryContainer, "onPrimaryContainer")
            ColorName(MaterialTheme.colorScheme.secondary, "secondary")
            ColorName(MaterialTheme.colorScheme.onSecondary, "onSecondary")
            ColorName(MaterialTheme.colorScheme.secondaryContainer, "secondaryContainer")
            ColorName(MaterialTheme.colorScheme.onSecondaryContainer, "onSecondaryContainer")
            ColorName(MaterialTheme.colorScheme.tertiary, "tertiary")
            ColorName(MaterialTheme.colorScheme.onTertiary, "onTertiary")
            ColorName(MaterialTheme.colorScheme.tertiaryContainer, "tertiaryContainer")
            ColorName(MaterialTheme.colorScheme.onTertiaryContainer, "onTertiaryContainer")
            ColorName(MaterialTheme.colorScheme.background, "background")
            ColorName(MaterialTheme.colorScheme.onBackground, "onBackground")
            ColorName(MaterialTheme.colorScheme.surface, "surface")
            ColorName(MaterialTheme.colorScheme.onSurface, "onSurface")
            ColorName(MaterialTheme.colorScheme.surfaceVariant, "surfaceVariant")
            ColorName(MaterialTheme.colorScheme.onSurfaceVariant, "onSurfaceVariant")
            ColorName(MaterialTheme.colorScheme.error, "error")
            ColorName(MaterialTheme.colorScheme.onError, "onError")
            ColorName(MaterialTheme.colorScheme.errorContainer, "errorContainer")
            ColorName(MaterialTheme.colorScheme.onErrorContainer, "onErrorContainer")
            ColorName(MaterialTheme.colorScheme.outline, "outline")
            ColorName(MaterialTheme.colorScheme.inverseOnSurface, "inverseOnSurface")
            ColorName(MaterialTheme.colorScheme.inverseSurface, "inverseSurface")
            ColorName(MaterialTheme.colorScheme.inversePrimary, "inversePrimary")
            ColorName(MaterialTheme.colorScheme.surfaceTint, "surfaceTint")
            ColorName(MaterialTheme.colorScheme.outlineVariant, "outlineVariant")
            ColorName(MaterialTheme.colorScheme.scrim, "scrim")
        }
    }
}