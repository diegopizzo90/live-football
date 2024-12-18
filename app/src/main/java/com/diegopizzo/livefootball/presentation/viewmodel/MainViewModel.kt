package com.diegopizzo.livefootball.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.diegopizzo.core.base.DispatcherProvider
import com.diegopizzo.core.base.ViewState
import com.diegopizzo.league.repository.LeagueRepository
import com.diegopizzo.livefootball.presentation.navigation.AppNavigator
import com.diegopizzo.livefootball.presentation.navigation.Destination
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch

class MainViewModel(
    private val appNavigator: AppNavigator,
    private val leagueRepository: LeagueRepository,
    override val defaultDispatcher: CoroutineDispatcher,
) : ViewModel(), DispatcherProvider {

    val navigationChannel = appNavigator.navigationChannel

    private val innerViewStates: MutableLiveData<ViewState<MainViewState>> = MutableLiveData()
    val viewStates: LiveData<ViewState<MainViewState>> = innerViewStates

    fun startFetchingLeagues() {
        backgroundScope.launch {
            leagueRepository.fetchLeagues()
                .onSuccess {
                    innerViewStates.postValue(ViewState.Success(MainViewState(isFetchingLeagues = false)))
                }
                .onFailure {
                    innerViewStates.postValue(ViewState.Error())
                }
        }
    }

    fun onSplashScreenAnimationFinished() {
        viewModelScope.launch {
            appNavigator.navigateTo(
                route = Destination.Home.route,
                popUpToRoute = Destination.Splash.route,
                isInclusive = true,
            )
        }
    }
}

data class MainViewState(
    val isFetchingLeagues: Boolean = true,
)
