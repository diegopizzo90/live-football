package com.diegopizzo.livefootball.core.config

import com.diegopizzo.livefootball.core.utils.DateUtils
import com.diegopizzo.livefootball.core.utils.DateUtilsImpl
import org.koin.dsl.module
import java.time.ZoneId
import java.util.Locale

val dateUtilsModule = module {
    factory<DateUtils> {
        DateUtilsImpl(ZoneId.systemDefault(), Locale.getDefault())
    }
}
