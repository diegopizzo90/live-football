package com.diegopizzo.match.presentation.view.util

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.diegopizzo.design.components.card.LFCardMatchViewData

class MatchScreenPreviewParameterProvider : PreviewParameterProvider<List<LFCardMatchViewData>> {
    override val values: Sequence<List<LFCardMatchViewData>>
        get() = listOf(matchViewDataList).asSequence()
}
