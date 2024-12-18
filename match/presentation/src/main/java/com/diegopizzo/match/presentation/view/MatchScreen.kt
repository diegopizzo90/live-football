@file:OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)

package com.diegopizzo.match.presentation.view

import android.content.res.Configuration
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.DatePickerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import com.diegopizzo.core.base.ViewState
import com.diegopizzo.design.components.card.LFCardMatch
import com.diegopizzo.design.components.chips.LFChipViewData
import com.diegopizzo.design.components.chips.LFChips
import com.diegopizzo.design.components.datepicker.LFCalendar
import com.diegopizzo.design.components.datepicker.LFDatePicker
import com.diegopizzo.design.components.datepicker.rememberLFCalendarState
import com.diegopizzo.design.components.divider.LFVerticalSpacer
import com.diegopizzo.design.components.snackbar.LFSnackbar
import com.diegopizzo.design.components.snackbar.toLFSnackbarViewData
import com.diegopizzo.design.components.toolbar.LFTopAppBar
import com.diegopizzo.design.screen.LFEmptyScreen
import com.diegopizzo.design.screen.LFErrorScreen
import com.diegopizzo.design.screen.LFLoadingScreen
import com.diegopizzo.design.theme.LFTheme
import com.diegopizzo.design.tokens.SpaceTokens
import com.diegopizzo.design.util.applyHazeEffect
import com.diegopizzo.design.util.conditional
import com.diegopizzo.match.presentation.R
import com.diegopizzo.match.presentation.view.util.MatchScreenPreviewParameterProvider
import com.diegopizzo.match.presentation.viewmodel.MatchViewEffect
import com.diegopizzo.match.presentation.viewmodel.MatchViewModel
import com.diegopizzo.match.presentation.viewmodel.MatchViewState
import com.diegopizzo.match.presentation.viewmodel.filterByMatchCriteria
import dev.chrisbanes.haze.HazeState
import dev.chrisbanes.haze.haze

@Composable
fun MatchScreen(
    viewModel: MatchViewModel,
) {
    val nullableViewState by viewModel.viewStates.observeAsState()
    val effect by viewModel.effect.collectAsState(null)

    val viewState = nullableViewState ?: return
    var showTopBar by remember { mutableStateOf(true) }
    val snackbarState = remember { SnackbarHostState() }

    val errorMessage: String = stringResource(R.string.something_went_wrong)

    LaunchedEffect(effect) {
        when (effect) {
            is MatchViewEffect.ShowSnackbar -> {
                snackbarState.showSnackbar(
                    visuals = (effect as MatchViewEffect.ShowSnackbar).viewData.visuals,
                )
            }

            null -> Unit
        }
    }

    Scaffold(
        topBar = {
            if (showTopBar) {
                LFTopAppBar(
                    title = stringResource(R.string.matches),
                )
            }
        },
        snackbarHost = {
            SnackbarContent(snackbarState)
        },
    ) { paddingValues ->

        when (viewState) {
            is ViewState.Success -> {
                showTopBar = true
                MatchScreenContent(
                    viewData = viewState.data,
                    currentYear = viewModel.currentYear(),
                    modifier = Modifier.padding(paddingValues),
                    onChipClick = {
                        viewModel.onChipClick(
                            chip = it,
                            currentViewState = viewState.data,
                        )
                    },
                    onDaySelected = {
                        viewModel.fetchMatches(
                            date = it,
                            showShimmer = true,
                            snackbarMessage = errorMessage,
                        )
                    },
                    onDaySelectedFromCalendar = {
                        val date = viewModel.getStringDate(it)
                        if (date != null) {
                            viewModel.fetchMatches(
                                date = date,
                                showShimmer = true,
                                snackbarMessage = errorMessage,
                            )
                        }
                    },
                )
            }

            is ViewState.Loading -> {
                when {
                    viewState.showShimmer -> {
                        showTopBar = true
                        LoadingMatchScreen(
                            modifier = Modifier.padding(paddingValues),
                        )
                    }

                    viewState.isLoading -> {
                        showTopBar = false
                        LFLoadingScreen(
                            modifier = Modifier.padding(paddingValues),
                        )
                    }
                }
            }

            is ViewState.Error -> {
                showTopBar = true
                LFErrorScreen(
                    modifier = Modifier.padding(paddingValues),
                )
            }
        }
    }
}

@Composable
private fun SnackbarContent(snackbarHostState: SnackbarHostState) {
    SnackbarHost(snackbarHostState) {
        LFSnackbar(
            viewData = it.toLFSnackbarViewData(),
        )
    }
}

@Composable
private fun MatchScreenContent(
    viewData: MatchViewState,
    currentYear: Int,
    modifier: Modifier = Modifier,
    onChipClick: (viewData: LFChipViewData) -> Unit = {},
    onDaySelected: (date: String) -> Unit = {},
    onDaySelectedFromCalendar: (dateMillis: Long) -> Unit = {},
) {
    val calendarState = rememberLFCalendarState(
        currentYear = currentYear,
        initialSelectedDateMillis = viewData.datePicker.first {
            it.selected
        }.millisUtc,
    )
    var showCalendar by remember { mutableStateOf(false) }

    Column(
        modifier = modifier,
    ) {
        if (viewData.matches.isNotEmpty()) {
            LFChips(
                viewData = viewData.leagues,
                onClick = onChipClick,
            )
            LFVerticalSpacer(height = SpaceTokens.MediumLarge)
        }
        LFDatePicker(
            viewData = viewData.datePicker,
            onClick = { onDaySelected(it) },
            onCalendarIconClick = { showCalendar = true },
        )
        LFVerticalSpacer(height = SpaceTokens.MediumLarge)
        Box {
            val hazeEffectState = remember { HazeState() }

            if (viewData.matches.isEmpty()) {
                LFEmptyScreen(
                    modifier = Modifier.haze(hazeEffectState),
                )
            } else {
                MatchListContent(
                    modifier = Modifier.haze(hazeEffectState),
                    viewData = viewData,
                )
            }
            CalendarOverlay(
                modifier = Modifier.applyHazeEffect(hazeEffectState),
                calendarState = calendarState,
                showCalendar = showCalendar,
                onDateSelected = {
                    onDaySelectedFromCalendar(it)
                },
                onDismiss = {
                    showCalendar = false
                },
            )
        }
    }
}

@Composable
private fun MatchListContent(
    viewData: MatchViewState,
    modifier: Modifier = Modifier,
) {
    val lazyListState = rememberLazyListState()
    val matchesFiltered = viewData.matches.filterByMatchCriteria(viewData.filterCriteria)

    LaunchedEffect(matchesFiltered.size) {
        lazyListState.animateScrollToItem(0)
    }
    LazyColumn(
        modifier = modifier,
        state = lazyListState,
        contentPadding = PaddingValues(
            start = SpaceTokens.ExtraLarge,
            end = SpaceTokens.ExtraLarge,
            bottom = SpaceTokens.ExtraLarge,
        ),
        verticalArrangement = Arrangement.spacedBy(SpaceTokens.ExtraLarge),
    ) {
        items(
            items = matchesFiltered,
            key = { item -> item.match.id },
        ) { match ->
            LFCardMatch(
                modifier = Modifier.animateItem(),
                viewData = match,
            )
        }
    }
}

@Composable
private fun CalendarOverlay(
    calendarState: DatePickerState,
    showCalendar: Boolean,
    onDateSelected: (dateMillis: Long) -> Unit,
    onDismiss: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val interactionSource = remember { MutableInteractionSource() }
    Box(
        modifier = Modifier.conditional(showCalendar) {
            then(modifier)
                .fillMaxSize()
                .clickable(
                    interactionSource = interactionSource,
                    indication = null,
                ) {
                    onDismiss()
                }
        },
    ) {
        AnimatedVisibility(
            visible = showCalendar,
            enter = expandVertically(
                animationSpec = spring(
                    stiffness = Spring.StiffnessMediumLow,
                ),
                expandFrom = Alignment.Top,
            ),
            exit = shrinkVertically(
                shrinkTowards = Alignment.Top,
            ),
        ) {
            LFCalendar(
                modifier = Modifier
                    .background(MaterialTheme.colorScheme.background)
                    .padding(bottom = SpaceTokens.MediumLarge),
                state = calendarState,
                onDateSelected = { dateSelected ->
                    onDismiss()
                    dateSelected?.let { date ->
                        onDateSelected(date)
                    }
                },
            )
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
        MatchScreenContent(
            viewData = viewData,
            currentYear = 2024,
            modifier = Modifier.background(MaterialTheme.colorScheme.background),
        )
    }
}
