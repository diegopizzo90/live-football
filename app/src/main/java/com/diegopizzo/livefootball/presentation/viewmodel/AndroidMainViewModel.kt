package com.diegopizzo.livefootball.presentation.viewmodel

import androidx.lifecycle.ViewModel
import com.diegopizzo.livefootball.core.base.DispatcherProvider
import com.diegopizzo.livefootball.league.domain.usecase.GetLeaguesUseCase
import com.diegopizzo.livefootball.presentation.navigation.AppNavigator
import kotlinx.coroutines.CoroutineDispatcher

class AndroidMainViewModel(
    appNavigator: AppNavigator,
    getLeaguesUseCase: GetLeaguesUseCase,
    override val defaultDispatcher: CoroutineDispatcher,
) : ViewModel(), DispatcherProvider {

    val sharedViewModel = MainViewModel(
        getLeaguesUseCase = getLeaguesUseCase,
        appNavigator = appNavigator,
        coroutineScope = backgroundScope,
        dispatcher = defaultDispatcher,
    )

    val navigationChannel = sharedViewModel.navigationChannel
    val viewStates = sharedViewModel.viewStates

    fun startFetchingLeagues() {
        sharedViewModel.startFetchingLeagues()
    }

    fun onSplashScreenAnimationFinished() {
        sharedViewModel.onSplashScreenAnimationFinished()
    }
}
