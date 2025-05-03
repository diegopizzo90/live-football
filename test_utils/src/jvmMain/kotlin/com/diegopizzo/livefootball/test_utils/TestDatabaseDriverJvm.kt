package com.diegopizzo.livefootball.test_utils

import app.cash.sqldelight.db.QueryResult
import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.db.SqlSchema
import app.cash.sqldelight.driver.jdbc.sqlite.JdbcSqliteDriver

actual suspend fun provideTestDriver(schema: SqlSchema<QueryResult.Value<Unit>>): SqlDriver {
    val driver = JdbcSqliteDriver(JdbcSqliteDriver.IN_MEMORY)
    schema.create(driver)
    return driver
}
