package com.diegopizzo.livefootball.presentation.viewmodel

import com.diegopizzo.livefootball.core.base.ViewState
import com.diegopizzo.livefootball.league.domain.usecase.GetLeaguesUseCase
import com.diegopizzo.livefootball.presentation.navigation.AppNavigator
import com.diegopizzo.livefootball.presentation.navigation.Destination
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MainViewModel(
    private val appNavigator: AppNavigator,
    private val getLeaguesUseCase: GetLeaguesUseCase,
    private val coroutineScope: CoroutineScope,
    private val dispatcher: CoroutineDispatcher,
) {

    val navigationChannel = appNavigator.navigationChannel

    private val innerViewStates: MutableStateFlow<ViewState<MainViewState>> = MutableStateFlow(ViewState.Loading())
    val viewStates: StateFlow<ViewState<MainViewState>> = innerViewStates

    fun startFetchingLeagues() {
        coroutineScope.launch(dispatcher) {
            getLeaguesUseCase()
                .onSuccess {
                    innerViewStates.value = ViewState.Success(MainViewState(isFetchingLeagues = false))
                }
                .onFailure {
                    innerViewStates.value = ViewState.Error()
                }
        }
    }

    fun onSplashScreenAnimationFinished() {
        coroutineScope.launch {
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
