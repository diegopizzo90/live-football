package com.diegopizzo.match.presentation.viewmodel

import androidx.compose.runtime.Immutable
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.diegopizzo.core.base.DispatcherProvider
import com.diegopizzo.core.base.ViewState
import com.diegopizzo.core.utils.DateUtils
import com.diegopizzo.design.components.card.LFCardMatchViewData
import com.diegopizzo.design.components.chips.LFChipViewData
import com.diegopizzo.design.components.datepicker.LFDatePickerViewData
import com.diegopizzo.match.presentation.mapper.MatchViewDataMapper
import com.diegopizzo.match.presentation.mapper.MatchViewDataMapper.Companion.LIVE_EVENT
import com.diegopizzo.match.presentation.usecase.GetMatchesByDateUseCase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.cancellable
import kotlinx.coroutines.launch

class MatchViewModel(
    private val getMatchesByDateUseCase: GetMatchesByDateUseCase,
    override val defaultDispatcher: CoroutineDispatcher,
    private val matchViewDataMapper: MatchViewDataMapper,
) : ViewModel(), DispatcherProvider {

    private val innerViewStates: MutableLiveData<ViewState<MatchViewState>> = MutableLiveData()
    val viewStates: LiveData<ViewState<MatchViewState>> = innerViewStates

    private var job: Job? = null
    private var currentMatchFilterCriteria: MatchFilterCriteria = MatchFilterCriteria()

    init {
        fetchMatches()
    }

    fun fetchMatches(date: String = DateUtils.getCurrentDate()) {
        job?.cancel() // cancel previous job
        job = backgroundScope.launch {
            innerViewStates.postValue(ViewState.Loading())
            getMatchesByDateUseCase(from = date, to = date)
                .cancellable()
                .collect { result ->
                    result.mapCatching {
                        matchViewDataMapper.mapViewData(it, currentMatchFilterCriteria, date)
                    }.onSuccess {
                        innerViewStates.postValue(ViewState.Success(it))
                    }.onFailure {
                        innerViewStates.postValue(ViewState.Error())
                    }
                }
        }
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

        innerViewStates.postValue(ViewState.Success(newViewState))
    }

    fun onDaySelected(date: String) {

    }

    private fun buildFilterCriteria(chip: LFChipViewData): MatchFilterCriteria {
        val filterCriteria = currentMatchFilterCriteria.copy(
            leagueId = if (!chip.selected) chip.id else null,
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

internal fun List<LFCardMatchViewData>.filterByMatchCriteria(criteria: MatchFilterCriteria): List<LFCardMatchViewData> {
    return when {
        criteria.leagueId != null -> {
            // Filter by league ID only
            this.filter { result -> result.match.leagueId == criteria.leagueId }
        }

        criteria.isLive -> {
            // Filter by live status only
            this.filter { result -> result.isLiveMatch }
        }

        else -> {
            // No filter applied, return the original list
            this
        }
    }
}
