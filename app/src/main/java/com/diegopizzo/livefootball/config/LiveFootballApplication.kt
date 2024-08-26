package com.diegopizzo.livefootball.config

import android.app.Application
import com.diegopizzo.core.config.ktorHttpClient
import com.diegopizzo.match.api.config.matchApiModule
import com.diegopizzo.league.config.leagueModule
import com.diegopizzo.livefootball.BuildConfig
import com.diegopizzo.livefootball.presentation.navigation.config.appNavigatorModule
import com.diegopizzo.livefootball.presentation.viewmodel.config.mainViewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class LiveFootballApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@LiveFootballApplication)
            modules(
                ktorHttpClient(BuildConfig.API_KEY),
                appNavigatorModule,
                mainViewModelModule,
                leagueModule,
                matchApiModule,
            )
        }
    }
}
