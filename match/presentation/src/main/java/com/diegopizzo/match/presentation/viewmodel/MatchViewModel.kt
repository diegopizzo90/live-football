package com.diegopizzo.match.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.diegopizzo.core.base.DispatcherProvider
import com.diegopizzo.core.base.ViewState
import com.diegopizzo.core.utils.DateUtils
import com.diegopizzo.design.components.card.LFCardMatchViewData
import com.diegopizzo.match.presentation.usecase.GetMatchesByDateUseCase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.cancellable
import kotlinx.coroutines.launch

class MatchViewModel(
    private val getMatchesByDateUseCase: GetMatchesByDateUseCase,
    override val defaultDispatcher: CoroutineDispatcher,
) : ViewModel(), DispatcherProvider {

    private val innerViewStates: MutableLiveData<ViewState<MatchViewState>> = MutableLiveData()
    val viewStates: LiveData<ViewState<MatchViewState>> = innerViewStates

    init {
        fetchMatches()
    }

    private var job: Job? = null

    private fun fetchMatches() {
        job?.cancel() // cancel previous job
        val today = DateUtils.getCurrentDate()
        job = backgroundScope.launch {
            innerViewStates.postValue(ViewState.Loading())
            getMatchesByDateUseCase(from = today, to = today)
                .cancellable()
                .collect { result ->
                    result.onSuccess {
                        innerViewStates.postValue(ViewState.Success(MatchViewState(matches = it)))
                    }.onFailure {
                        innerViewStates.postValue(ViewState.Error())
                    }
                }
        }
    }
}

data class MatchViewState(
    val matches: List<LFCardMatchViewData>,
)
