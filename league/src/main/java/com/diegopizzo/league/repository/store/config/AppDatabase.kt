package com.diegopizzo.league.repository.store.config

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.diegopizzo.league.repository.store.converter.LeagueTypeConverter
import com.diegopizzo.league.repository.store.dao.LeagueDao
import com.diegopizzo.league.repository.store.entity.LeagueEntity

@Database(
    entities = [LeagueEntity::class],
    version = 1,
)
@TypeConverters(value = [LeagueTypeConverter::class])
abstract class AppDatabase : RoomDatabase() {
    abstract fun leagueDao(): LeagueDao
}