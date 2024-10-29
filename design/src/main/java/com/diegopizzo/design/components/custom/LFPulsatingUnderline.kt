package com.diegopizzo.design.components.custom

import android.content.res.Configuration
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.diegopizzo.design.components.text.LFHeadlineSmall
import com.diegopizzo.design.theme.LFTheme
import com.diegopizzo.design.tokens.ShapeTokens
import com.diegopizzo.design.tokens.SpaceTokens

@Composable
fun LFPulsatingUnderline(
    modifier: Modifier = Modifier,
    targetValue: Float = 20f,
    animationDuration: Int = 1000,
    color: Color = MaterialTheme.colorScheme.onSurface,
    content: @Composable () -> Unit,
) {
    val infiniteTransition = rememberInfiniteTransition(label = "PulsatingLine")

    val width by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = targetValue,
        animationSpec = infiniteRepeatable(
            animation = tween(animationDuration, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse,
        ),
        label = "PulsatingLine",
    )

    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        content()
        Box(
            modifier = Modifier
                .background(color, shape = ShapeTokens.CornerLarge)
                .width(width.dp)
                .height(1.dp)
                .align(Alignment.CenterHorizontally),
        )
    }
}

@Preview("Default", "LFPulsatingUnderline", showBackground = true)
@Preview("Dark theme", "LFPulsatingUnderline", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun LFPulsatingUnderlinePreview() {
    LFTheme {
        LFPulsatingUnderline(
            modifier = Modifier.padding(SpaceTokens.MediumLarge),
        ) {
            LFHeadlineSmall(
                text = "37'",
            )
        }
    }
}
