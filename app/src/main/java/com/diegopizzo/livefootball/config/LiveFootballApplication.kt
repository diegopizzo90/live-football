package com.diegopizzo.livefootball.config

import android.app.Application
import com.diegopizzo.core.config.dateUtilsModule
import com.diegopizzo.core.config.fakeKtorHttpClient
import com.diegopizzo.core.config.ktorHttpClient
import com.diegopizzo.league.config.leagueModule
import com.diegopizzo.livefootball.BuildConfig
import com.diegopizzo.livefootball.presentation.navigation.config.appNavigatorModule
import com.diegopizzo.livefootball.presentation.viewmodel.config.mainViewModelModule
import com.diegopizzo.match.api.config.matchApiModule
import com.diegopizzo.match.presentation.config.matchPresentationModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class LiveFootballApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@LiveFootballApplication)
            modules(
                if (BuildConfig.IS_FAKE_NETWORK_RESPONSE) fakeKtorHttpClient() else ktorHttpClient(BuildConfig.API_KEY),
                appNavigatorModule,
                mainViewModelModule,
                leagueModule,
                dateUtilsModule,
                matchApiModule,
                matchPresentationModule,
            )
        }
    }
}
