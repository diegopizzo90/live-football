package com.diegopizzo.livefootball.presentation.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.diegopizzo.core.base.ViewState
import com.diegopizzo.design.components.divider.LFVerticalSpacer
import com.diegopizzo.design.components.text.LFDisplayLarge
import com.diegopizzo.design.tokens.SpaceTokens
import com.diegopizzo.livefootball.R
import com.diegopizzo.livefootball.presentation.viewmodel.MainViewModel

@Composable
fun SplashScreen(
    viewModel: MainViewModel,
) {
    val nullableViewState by viewModel.viewStates.observeAsState()
    val viewState = nullableViewState ?: return

    when (viewState) {
        is ViewState.Success -> {
            Box(
                modifier = Modifier
                    .background(MaterialTheme.colorScheme.background)
                    .fillMaxSize(),
                contentAlignment = Alignment.Center,
            ) {
                val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.splash_screen_ball))
                val logoAnimationState = animateLottieCompositionAsState(composition = composition, iterations = 5)
                Column {
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
                        text = "LiveFootball",
                        textAlign = TextAlign.Center,
                    )
                }
            }
        }

        else -> Unit
    }
}
