package com.diegopizzo.design.screen

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.diegopizzo.design.R
import com.diegopizzo.design.components.divider.LFVerticalSpacer
import com.diegopizzo.design.components.text.LFBodyLarge
import com.diegopizzo.design.components.text.LFHeadingLarge
import com.diegopizzo.design.theme.LFTheme
import com.diegopizzo.design.tokens.SpaceTokens

@Composable
fun LFErrorScreen(
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .background(MaterialTheme.colorScheme.background)
            .fillMaxSize(),
        contentAlignment = Alignment.Center,
    ) {
        val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.error))
        val logoAnimationState =
            animateLottieCompositionAsState(composition = composition, iterations = 1)
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            LottieAnimation(
                composition = composition,
                progress = { logoAnimationState.progress },
            )
            LFVerticalSpacer(height = SpaceTokens.Large)
            LFHeadingLarge(
                text = "Something went wrong",
                textAlign = TextAlign.Center,
            )
            LFVerticalSpacer(height = SpaceTokens.Small)
            LFBodyLarge(
                text = "Please try again later",
                textAlign = TextAlign.Center,
                color = MaterialTheme.colorScheme.onSurface,
            )
        }
    }
}

@Preview("Default", "LFErrorScreen")
@Preview("Dark theme", "LFErrorScreen", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun LFErrorScreenPreview() {
    LFTheme {
        LFErrorScreen()
    }
}
