package com.diegopizzo.livefootball.match.presentation.viewmodel

import androidx.lifecycle.ViewModel
import com.diegopizzo.livefootball.core.base.DispatcherProvider
import com.diegopizzo.livefootball.core.utils.DateUtils
import com.diegopizzo.livefootball.design.components.chips.LFChipViewData
import com.diegopizzo.livefootball.match.presentation.mapper.MatchViewDataMapper
import kotlinx.coroutines.CoroutineDispatcher

class AndroidMatchViewModel(
    matchCoordinator: MatchCoordinator,
    override val defaultDispatcher: CoroutineDispatcher,
    matchViewDataMapper: MatchViewDataMapper,
    dateUtils: DateUtils,
) : ViewModel(), DispatcherProvider {

    val sharedViewModel = MatchViewModel(
        matchCoordinator = matchCoordinator,
        coroutineScope = backgroundScope,
        dispatcher = defaultDispatcher,
        matchViewDataMapper = matchViewDataMapper,
        dateUtils = dateUtils,
    )

    val viewStates = sharedViewModel.viewStates
    val effect = sharedViewModel.effect

    fun fetchMatches(date: String, showShimmer: Boolean = false, snackbarMessage: String? = null) {
        sharedViewModel.fetchMatches(date, showShimmer, snackbarMessage)
    }

    fun onChipClick(chip: LFChipViewData, currentViewState: MatchViewState) {
        sharedViewModel.onChipClick(chip, currentViewState)
    }

    fun getStringDate(dateMillis: Long): String? = sharedViewModel.getStringDate(dateMillis)
    fun currentYear(): Int = sharedViewModel.currentYear()
}
