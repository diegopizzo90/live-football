package com.diegopizzo.livefootball.match.presentation.view.util

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.diegopizzo.livefootball.match.presentation.viewmodel.MatchViewState

class MatchScreenPreviewParameterProvider : PreviewParameterProvider<MatchViewState> {
    override val values: Sequence<MatchViewState>
        get() = listOf(matchViewDataList).asSequence()
}
