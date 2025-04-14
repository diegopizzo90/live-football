package com.diegopizzo.livefootball.presentation.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.diegopizzo.livefootball.R
import com.diegopizzo.livefootball.core.base.ViewState
import com.diegopizzo.livefootball.design.components.divider.LFVerticalSpacer
import com.diegopizzo.livefootball.design.components.text.LFDisplayLarge
import com.diegopizzo.livefootball.design.screen.LFErrorScreen
import com.diegopizzo.livefootball.design.tokens.SpaceTokens
import com.diegopizzo.livefootball.presentation.viewmodel.AndroidMainViewModel

@Composable
fun SplashScreen(
    viewModel: AndroidMainViewModel,
) {
    val nullableViewState by viewModel.viewStates.collectAsState()
    val viewState = nullableViewState

    when (viewState) {
        is ViewState.Success -> {
            Box(
                modifier = Modifier
                    .background(MaterialTheme.colorScheme.background)
                    .fillMaxSize(),
                contentAlignment = Alignment.Center,
            ) {
                val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.splash_screen))
                val logoAnimationState = animateLottieCompositionAsState(composition = composition, iterations = 1)
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    LottieAnimation(
                        composition = composition,
                        progress = { logoAnimationState.progress },
                    )
                    if (logoAnimationState.isAtEnd &&
                        logoAnimationState.isPlaying &&
                        !viewState.data.isFetchingLeagues
                    ) {
                        viewModel.onSplashScreenAnimationFinished()
                    }
                    LFVerticalSpacer(height = SpaceTokens.Large)
                    LFDisplayLarge(
                        text = stringResource(R.string.livefootball),
                        textAlign = TextAlign.Center,
                    )
                }
            }
        }

        is ViewState.Error -> {
            LFErrorScreen()
        }

        else -> Unit
    }
}
