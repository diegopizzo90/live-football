package com.diegopizzo.league.repository.store.entity

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.diegopizzo.league.config.LeagueType

@Entity(tableName = "league", indices = [Index(value = ["name"], unique = true)])
internal data class LeagueEntity(
    @PrimaryKey val leagueId: Long,
    val name: String,
    val logo: String,
    val countryName: String? = null,
    val countryCode: String? = null,
    val leagueType: LeagueType,
)
