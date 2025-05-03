package com.diegopizzo.livefootball.core.config

import com.diegopizzo.livefootball.core.utils.DateUtils
import com.diegopizzo.livefootball.core.utils.DateUtilsImpl
import kotlinx.datetime.TimeZone
import org.koin.dsl.module

val dateUtilsModule = module {
    factory<DateUtils> {
        DateUtilsImpl(timeZone = TimeZone.currentSystemDefault())
    }
}
