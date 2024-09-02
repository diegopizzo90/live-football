package com.diegopizzo.league.repository

import com.diegopizzo.league.config.LeaguesAvailable
import com.diegopizzo.league.repository.model.LeagueData
import com.diegopizzo.league.repository.store.LeagueStore
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope

interface LeagueRepository {
    suspend fun fetchLeagues(): Result<Unit>
    suspend fun getLeagues(): Result<List<LeagueData>>
}

internal class LeagueRepositoryImpl(
    private val leagueStore: LeagueStore,
) : LeagueRepository {
    override suspend fun fetchLeagues(): Result<Unit> {
        return coroutineScope {
            try {
                // Collect results from all league fetch operations
                val results = LeaguesAvailable.entries.map { league ->
                    async {
                        leagueStore.getLeague(league)
                    }
                }.awaitAll()

                // Check for any failures in the results
                results.forEach { result ->
                    if (result.isFailure) {
                        return@coroutineScope Result.failure<Unit>(
                            result.exceptionOrNull() ?: Exception("Unknown error occurred"),
                        )
                    }
                }

                // If all operations were successful, return success
                Result.success(Unit)
            } catch (e: Exception) {
                // Handle any exceptions that may have occurred during the process
                Result.failure(e)
            }
        }
    }

    override suspend fun getLeagues(): Result<List<LeagueData>> {
        return coroutineScope {
            try {
                // Fetch all leagues concurrently and aggregate results
                val leagues = LeaguesAvailable.entries.map { league ->
                    async { leagueStore.getLeague(league) }
                }.awaitAll().map { it.getOrThrow() }

                Result.success(leagues)
            } catch (e: Exception) {
                // Handle any exceptions that may have occurred during the process
                Result.failure(e)
            }
        }
    }
}
