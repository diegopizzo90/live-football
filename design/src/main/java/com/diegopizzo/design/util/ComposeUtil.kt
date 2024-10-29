package com.diegopizzo.design.util

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.diegopizzo.design.theme.ColorPalette
import dev.chrisbanes.haze.HazeDefaults
import dev.chrisbanes.haze.HazeDefaults.tint
import dev.chrisbanes.haze.HazeState
import dev.chrisbanes.haze.HazeTint
import dev.chrisbanes.haze.haze

@Composable
fun Modifier.conditional(
    condition: Boolean,
    onTrue: @Composable Modifier.() -> Modifier,
) = conditionalInternal(condition, onTrue = onTrue)

@Composable
fun Modifier.conditional(
    condition: Boolean,
    onTrue: @Composable Modifier.() -> Modifier,
    onFalse: @Composable Modifier.() -> Modifier,
) = conditionalInternal(condition, onTrue, onFalse)

@Composable
private fun Modifier.conditionalInternal(
    condition: Boolean,
    onTrue: @Composable Modifier.() -> Modifier,
    onFalse: (@Composable Modifier.() -> Modifier)? = null,
) = if (condition) {
    then(onTrue())
} else {
    onFalse?.let { then(Modifier.it()) }
} ?: this

fun Modifier.shimmerLoadingAnimation(
    isLoadingCompleted: Boolean = true,
    widthOfShadowBrush: Int = 500,
    angleOfAxisY: Float = 270f,
    durationMillis: Int = 1000,
): Modifier {
    if (isLoadingCompleted) {
        return this
    } else {
        return composed {
            val baseColor = MaterialTheme.colorScheme.surface
            val shimmerColors = listOf(
                baseColor.copy(alpha = 0.3f),
                baseColor.copy(alpha = 0.5f),
                baseColor.copy(alpha = 1.0f),
                baseColor.copy(alpha = 0.5f),
                baseColor.copy(alpha = 0.3f),
            )

            val transition = rememberInfiniteTransition(label = "")

            val translateAnimation = transition.animateFloat(
                initialValue = 0f,
                targetValue = (durationMillis + widthOfShadowBrush).toFloat(),
                animationSpec = infiniteRepeatable(
                    animation = tween(
                        durationMillis = durationMillis,
                        easing = LinearEasing,
                    ),
                    repeatMode = RepeatMode.Restart,
                ),
                label = "Shimmer loading animation",
            )

            this.background(
                brush = Brush.linearGradient(
                    colors = shimmerColors,
                    start = Offset(x = translateAnimation.value - widthOfShadowBrush, y = 0.0f),
                    end = Offset(x = translateAnimation.value, y = angleOfAxisY),
                ),
            )
        }
    }
}

@Composable
fun measureTextWidth(text: String, textStyle: TextStyle = TextStyle.Default): Dp {
    val textMeasurer = rememberTextMeasurer()
    val widthInPixels = textMeasurer.measure(text, textStyle).size.width
    return with(LocalDensity.current) { widthInPixels.toDp() }
}

@Composable
fun Modifier.applyHazeEffect(
    state: HazeState,
): Modifier = haze(
    state = state,
    style = HazeDefaults.style(
        backgroundColor = MaterialTheme.colorScheme.background,
        blurRadius = 6.dp,
        tint = HazeTint.Color(tint(ColorPalette.Dark6)),
    ),
)
