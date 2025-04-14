package com.diegopizzo.livefootball.match.presentation.viewmodel

import androidx.compose.runtime.Immutable
import com.diegopizzo.livefootball.core.base.ViewState
import com.diegopizzo.livefootball.core.utils.DateUtils
import com.diegopizzo.livefootball.design.components.card.LFCardMatchViewData
import com.diegopizzo.livefootball.design.components.chips.LFChipViewData
import com.diegopizzo.livefootball.design.components.datepicker.LFDatePickerViewData
import com.diegopizzo.livefootball.design.components.snackbar.LFSnackBarViewData
import com.diegopizzo.livefootball.match.presentation.mapper.MatchViewDataMapper
import com.diegopizzo.livefootball.match.presentation.mapper.MatchViewDataMapper.Companion.LIVE_EVENT
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.cancellable
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class MatchViewModel(
    private val matchCoordinator: MatchCoordinator,
    private val coroutineScope: CoroutineScope,
    private val dispatcher: CoroutineDispatcher,
    private val matchViewDataMapper: MatchViewDataMapper,
    private val dateUtils: DateUtils,
) {

    private val innerViewStates: MutableStateFlow<ViewState<MatchViewState>> = MutableStateFlow(ViewState.Loading())
    internal val viewStates: StateFlow<ViewState<MatchViewState>> = innerViewStates

    private val currentViewData: MatchViewState?
        get() = (viewStates.value as? ViewState.Success)?.data

    private val innerEffect: Channel<MatchViewEffect> = Channel()
    val effect = innerEffect.receiveAsFlow()

    private var job: Job? = null
    private var currentDateSelected: String? = null
    private var currentMatchFilterCriteria: MatchFilterCriteria = MatchFilterCriteria()

    init {
        fetchMatches()
    }

    fun fetchMatches(
        date: String = dateUtils.getCurrentDate(),
        showShimmer: Boolean = false,
        snackbarMessage: String? = null,
    ) {
        if (date == currentDateSelected) return
        currentDateSelected = date
        clearFilter()
        job?.cancel() // cancel previous job
        innerViewStates.value = ViewState.Loading(showShimmer = showShimmer)
        job = coroutineScope.launch(dispatcher) {
            matchCoordinator.fetchMatches(date = date)
                .cancellable()
                .collect { result ->
                    result.mapCatching {
                        matchViewDataMapper.mapViewData(it, currentMatchFilterCriteria, date)
                    }.onSuccess {
                        innerViewStates.value = ViewState.Success(it)
                    }.onFailure {
                        onError(snackbarMessage)
                    }
                }
        }
    }

    private suspend fun onError(snackbarMessage: String?) {
        if (snackbarMessage != null && currentViewData != null) {
            innerViewStates.value = ViewState.Success(currentViewData!!)
            showSnackbar(message = snackbarMessage)
        } else {
            innerViewStates.value = ViewState.Error()
        }
    }

    fun getStringDate(dateMillis: Long): String? {
        return dateUtils.getDateFromMilliseconds(dateMillis)
    }

    fun currentYear(): Int = dateUtils.currentYear()

    private fun clearFilter() {
        currentMatchFilterCriteria = MatchFilterCriteria()
    }

    private fun stopLoading() {
        innerViewStates.value = ViewState.Loading(isLoading = false, showShimmer = false)
    }

    fun onChipClick(chip: LFChipViewData, currentViewState: MatchViewState) {
        val updatedLeagues = currentViewState.leagues.map { league ->
            if (league.id == chip.id) {
                league.copy(selected = !league.selected)
            } else {
                league.copy(selected = false)
            }
        }

        val newViewState = currentViewState.copy(
            filterCriteria = buildFilterCriteria(chip),
            leagues = updatedLeagues,
        )

        innerViewStates.value = ViewState.Success(newViewState)
    }

    private suspend fun showSnackbar(message: String) {
        innerEffect.send(
            MatchViewEffect.ShowSnackbar(
                viewData = LFSnackBarViewData(
                    message = message,
                ),
            ),
        )
    }

    private fun buildFilterCriteria(chip: LFChipViewData): MatchFilterCriteria {
        val filterCriteria = currentMatchFilterCriteria.copy(
            leagueId = if (!chip.selected && chip.id != 0L) chip.id else null,
            isLive = !chip.selected && chip.text == LIVE_EVENT,
        )
        currentMatchFilterCriteria = filterCriteria
        return filterCriteria
    }
}

@Immutable
data class MatchViewState(
    val filterCriteria: MatchFilterCriteria = MatchFilterCriteria(),
    val datePicker: List<LFDatePickerViewData>,
    val leagues: List<LFChipViewData>,
    val matches: List<LFCardMatchViewData>,
)

@Immutable
data class MatchFilterCriteria(
    val leagueId: Long? = null,
    val isLive: Boolean = false,
)

sealed class MatchViewEffect {
    class ShowSnackbar(val viewData: LFSnackBarViewData) : MatchViewEffect()
}

internal fun List<LFCardMatchViewData>.filterByMatchCriteria(criteria: MatchFilterCriteria): List<LFCardMatchViewData> {
    return when {
        criteria.leagueId != null -> {
            // Filter by league ID only
            this.filter { result -> result.match.leagueId == criteria.leagueId }
        }

        criteria.isLive -> {
            // Filter by live status only
            this.filter { result -> result.match.isLiveMatch }
        }

        else -> {
            // No filter applied, return the original list
            this
        }
    }
}
