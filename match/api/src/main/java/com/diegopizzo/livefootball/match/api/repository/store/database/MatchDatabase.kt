package com.diegopizzo.livefootball.match.api.repository.store.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.diegopizzo.livefootball.match.api.repository.store.dao.MatchDao
import com.diegopizzo.livefootball.match.api.repository.store.entity.MatchEntity
import com.diegopizzo.livefootball.match.api.repository.store.entity.MatchResponseEntity

@Database(
    entities = [MatchResponseEntity::class, MatchEntity::class],
    version = 2,
)
internal abstract class MatchDatabase : RoomDatabase() {
    abstract fun matchDao(): MatchDao
}

internal val migration1_2 = object : Migration(1, 2) {
    override fun migrate(db: SupportSQLiteDatabase) {
        db.execSQL("CREATE INDEX IF NOT EXISTS index_match_matchResponseFkId ON match(matchResponseFkId)")
    }
}
