package com.diegopizzo.fixture.api.repository

import com.diegopizzo.fixture.api.repository.store.FixtureStore

interface FixtureRepository {

}

internal class FixtureRepositoryImpl(
    private val store: FixtureStore,
) : FixtureRepository {

}
