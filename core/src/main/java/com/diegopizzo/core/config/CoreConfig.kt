package com.diegopizzo.core.config

import com.diegopizzo.core.utils.DateUtils
import com.diegopizzo.core.utils.DateUtilsImpl
import org.koin.dsl.module
import java.time.ZoneId
import java.util.Locale

val dateUtilsModule = module {
    factory<DateUtils> {
        DateUtilsImpl(ZoneId.systemDefault(), Locale.getDefault())
    }
}
