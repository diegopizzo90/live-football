package com.diegopizzo.match.api.repository.store.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.diegopizzo.match.api.repository.store.dao.MatchDao
import com.diegopizzo.match.api.repository.store.entity.MatchEntity
import com.diegopizzo.match.api.repository.store.entity.MatchResponseEntity

@Database(
    entities = [MatchResponseEntity::class, MatchEntity::class],
    version = 1,
)
internal abstract class MatchDatabase : RoomDatabase() {
    abstract fun matchDao(): MatchDao
}
