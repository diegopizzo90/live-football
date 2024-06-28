package com.diegopizzo.livefootball.config

import android.app.Application
import com.diegopizzo.core.config.ktorHttpClient
import com.diegopizzo.livefootball.BuildConfig
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class LiveFootballApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@LiveFootballApplication)
            modules(
                ktorHttpClient(BuildConfig.API_KEY),
            )
        }
    }
}
