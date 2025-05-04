package com.diegopizzo.livefootball.presentation.navigation.config

import com.diegopizzo.livefootball.presentation.navigation.AppNavigator
import com.diegopizzo.livefootball.presentation.navigation.AppNavigatorImpl
import org.koin.dsl.module

val appNavigatorModule = module {
    single<AppNavigator> {
        AppNavigatorImpl()
    }
}
