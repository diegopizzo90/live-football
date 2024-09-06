package com.diegopizzo.match.presentation.view

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import com.diegopizzo.core.base.ViewState
import com.diegopizzo.design.components.card.LFCardMatch
import com.diegopizzo.design.components.chips.LFChipViewData
import com.diegopizzo.design.components.chips.LFChips
import com.diegopizzo.design.components.toolbar.LFTopAppBar
import com.diegopizzo.design.screen.LFEmptyScreen
import com.diegopizzo.design.screen.LFErrorScreen
import com.diegopizzo.design.screen.LFLoadingScreen
import com.diegopizzo.design.theme.LFTheme
import com.diegopizzo.design.tokens.SpaceTokens
import com.diegopizzo.match.presentation.R
import com.diegopizzo.match.presentation.view.util.MatchScreenPreviewParameterProvider
import com.diegopizzo.match.presentation.viewmodel.MatchViewModel
import com.diegopizzo.match.presentation.viewmodel.MatchViewState
import com.diegopizzo.match.presentation.viewmodel.filterByMatchCriteria

@Composable
fun MatchScreen(
    viewModel: MatchViewModel,
) {

    val nullableViewState by viewModel.viewStates.observeAsState()

    LaunchedEffect(Unit) {
        viewModel.fetchMatches()
    }

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
                    viewData = viewState.data,
                    onChipClick = {
                        viewModel.onChipClick(
                            chip = it,
                            currentViewState = viewState.data,
                        )
                    },
                )
            }
        }
    }
}

@Composable
private fun MatchScreenContent(
    modifier: Modifier = Modifier,
    viewData: MatchViewState,
    onChipClick: (viewData: LFChipViewData) -> Unit = {},
) {
    Scaffold(
        modifier = modifier,
        topBar = {
            LFTopAppBar(title = stringResource(R.string.matches))
        },
    ) {
        Column(
            modifier = Modifier.padding(it),
        ) {
            LFChips(
                viewData = viewData.leagues,
                onClick = onChipClick,
            )
            LazyColumn(
                contentPadding = PaddingValues(
                    horizontal = SpaceTokens.ExtraLarge,
                    vertical = SpaceTokens.Medium,
                ),
                verticalArrangement = Arrangement.spacedBy(SpaceTokens.ExtraLarge),
            ) {

                val matchesFiltered = viewData.matches.filterByMatchCriteria(viewData.filterCriteria)

                items(matchesFiltered) { matchFiltered ->
                    LFCardMatch(viewData = matchFiltered)
                }
            }
        }
    }
}

@Preview("Default", "MatchScreenContent")
@Preview("Dark theme", "MatchScreenContent", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun MatchScreenPreview(
    @PreviewParameter(MatchScreenPreviewParameterProvider::class)
    viewData: MatchViewState,
) {
    LFTheme {
        MatchScreenContent(viewData = viewData)
    }
}
