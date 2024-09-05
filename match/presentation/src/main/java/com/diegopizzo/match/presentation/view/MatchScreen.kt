package com.diegopizzo.match.presentation.view

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import com.diegopizzo.core.base.ViewState
import com.diegopizzo.design.components.card.LFCardMatch
import com.diegopizzo.design.components.card.LFCardMatchViewData
import com.diegopizzo.design.components.toolbar.LFTopAppBar
import com.diegopizzo.design.screen.LFEmptyScreen
import com.diegopizzo.design.screen.LFErrorScreen
import com.diegopizzo.design.screen.LFLoadingScreen
import com.diegopizzo.design.theme.LFTheme
import com.diegopizzo.design.tokens.SpaceTokens
import com.diegopizzo.match.presentation.R
import com.diegopizzo.match.presentation.view.util.MatchScreenPreviewParameterProvider
import com.diegopizzo.match.presentation.viewmodel.MatchViewModel

@Composable
fun MatchScreen(
    viewModel: MatchViewModel,
) {
    val nullableViewState by viewModel.viewStates.observeAsState()
    val viewState = nullableViewState ?: return

    when (viewState) {
        is ViewState.Error -> {
            LFErrorScreen()
        }

        is ViewState.Loading -> {
            LFLoadingScreen()
        }

        is ViewState.Success -> {
            if (viewState.data.matches.isEmpty()) {
                LFEmptyScreen()
            } else {
                MatchScreenContent(
                    modifier = Modifier,
                    matches = viewState.data.matches,
                )
            }
        }
    }
}

@Composable
private fun MatchScreenContent(
    modifier: Modifier = Modifier,
    matches: List<LFCardMatchViewData>,
) {
    Scaffold(
        modifier = modifier,
        topBar = {
            LFTopAppBar(title = stringResource(R.string.matches))
        },
    ) {
        LazyColumn(
            modifier = Modifier.padding(it),
            contentPadding = PaddingValues(
                horizontal = SpaceTokens.ExtraLarge,
                vertical = SpaceTokens.Medium,
            ),
            verticalArrangement = Arrangement.spacedBy(SpaceTokens.ExtraLarge),
        ) {
            items(matches) { result ->
                LFCardMatch(viewData = result)
            }
        }
    }
}

@Preview("Default", "MatchScreenContent")
@Preview("Dark theme", "MatchScreenContent", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun MatchScreenPreview(
    @PreviewParameter(MatchScreenPreviewParameterProvider::class)
    viewData: List<LFCardMatchViewData>,
) {
    LFTheme {
        MatchScreenContent(matches = viewData)
    }
}
