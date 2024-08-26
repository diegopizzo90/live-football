package com.diegopizzo.match.api.repository

import com.diegopizzo.match.api.repository.store.MatchStore

interface MatchRepository {

}

internal class MatchRepositoryImpl(
    private val store: MatchStore,
) : MatchRepository {

}
