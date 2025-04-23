package com.diegopizzo.livefootball.config

import android.app.Application
import android.content.Context
import app.cash.sqldelight.db.QueryResult
import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.db.SqlSchema
import app.cash.sqldelight.driver.android.AndroidSqliteDriver
import com.diegopizzo.livefootball.BuildConfig
import com.diegopizzo.livefootball.core.config.dateUtilsModule
import com.diegopizzo.livefootball.core.config.fakeKtorHttpClient
import com.diegopizzo.livefootball.core.config.ktorHttpClient
import com.diegopizzo.livefootball.core.utils.SqlDriverFactory
import com.diegopizzo.livefootball.league.api.config.leagueApiModule
import com.diegopizzo.livefootball.league.domain.config.leagueDomainModule
import com.diegopizzo.livefootball.match.api.config.matchApiModule
import com.diegopizzo.livefootball.match.domain.config.matchDomainModule
import com.diegopizzo.livefootball.match.presentation.config.matchPresentationModule
import com.diegopizzo.livefootball.presentation.navigation.config.appNavigatorModule
import com.diegopizzo.livefootball.presentation.viewmodel.config.androidMainViewModelModule
import io.ktor.client.engine.android.AndroidClientEngine
import io.ktor.client.engine.android.AndroidEngineConfig
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class LiveFootballApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@LiveFootballApplication)
            modules(
                if (BuildConfig.IS_FAKE_NETWORK_RESPONSE) fakeKtorHttpClient() else ktorHttpClient(
                    BuildConfig.API_KEY,
                    AndroidClientEngine(AndroidEngineConfig()),
                ),
                appNavigatorModule,
                androidMainViewModelModule,
                leagueApiModule(AndroidSqlDriverFactory(this@LiveFootballApplication)),
                leagueDomainModule,
                dateUtilsModule,
                matchApiModule,
                matchDomainModule,
                matchPresentationModule,
            )
        }
    }
}

class AndroidSqlDriverFactory(
    private val context: Context,
) : SqlDriverFactory {
    override fun createDriver(schema: SqlSchema<QueryResult.Value<Unit>>, name: String): SqlDriver {
        return AndroidSqliteDriver(
            schema = schema,
            context = context,
            name = name,
        )
    }
}
