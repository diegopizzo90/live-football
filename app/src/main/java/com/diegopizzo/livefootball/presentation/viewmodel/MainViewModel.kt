package com.diegopizzo.livefootball.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.diegopizzo.livefootball.presentation.navigation.AppNavigator
import com.diegopizzo.livefootball.presentation.navigation.Destination
import kotlinx.coroutines.launch

class MainViewModel(private val appNavigator: AppNavigator) : ViewModel() {

    val navigationChannel = appNavigator.navigationChannel

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
