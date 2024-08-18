package com.diegopizzo.league.repository

import com.diegopizzo.league.config.LeaguesAvailable
import com.diegopizzo.league.repository.store.LeagueStore
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.withContext

interface LeagueRepository {
    suspend fun fetchLeagues(): Result<Unit>
}

internal class LeagueRepositoryImpl(
    private val leagueStore: LeagueStore,
    private val defaultDispatcher: CoroutineDispatcher,
) : LeagueRepository {
    override suspend fun fetchLeagues(): Result<Unit> {
        return withContext(defaultDispatcher) {
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
                        return@withContext Result.failure<Unit>(
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
}
