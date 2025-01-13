package com.diegopizzo.livefootball.league.repository.store.converter

import androidx.room.TypeConverter
import com.diegopizzo.livefootball.league.config.LeagueType

class LeagueTypeConverter {
    @TypeConverter
    fun toLeagueType(value: String) = enumValueOf<LeagueType>(value)

    @TypeConverter
    fun fromLeagueType(value: LeagueType) = value.name
}
