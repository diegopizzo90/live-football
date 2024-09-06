package com.diegopizzo.core.config

import android.util.Log
import io.ktor.client.HttpClient
import io.ktor.client.engine.mock.MockEngine
import io.ktor.client.engine.mock.respond
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.plugins.observer.ResponseObserver
import io.ktor.http.ContentType
import io.ktor.http.HttpStatusCode
import io.ktor.http.headersOf
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.koin.dsl.module

private val responseHeaders = headersOf("Content-Type" to listOf(ContentType.Application.Json.toString()))

fun fakeKtorHttpClient() = module {
    single {
        HttpClient(MockEngine) {
            install(ContentNegotiation) {
                json(
                    Json {
                        prettyPrint = true
                        isLenient = true
                        useAlternativeNames = true
                        ignoreUnknownKeys = true
                        encodeDefaults = false
                    },
                )
            }

            install(HttpTimeout) {
                requestTimeoutMillis = NETWORK_TIME_OUT
                connectTimeoutMillis = NETWORK_TIME_OUT
                socketTimeoutMillis = NETWORK_TIME_OUT
            }

            install(Logging) {
                logger = object : Logger {
                    override fun log(message: String) {
                        Log.v("Logger Ktor =>", message)
                    }
                }
                level = LogLevel.ALL
            }

            install(ResponseObserver) {
                onResponse { response ->
                    Log.d("FAKE HTTP call:", "${response.status.value}")
                }
            }

            engine {
                addHandler { request ->
                    when {
                        request.url.encodedPath.contains("/fixtures") &&
                            request.url.encodedQuery.contains("league=135") -> {
                            respond(matchesSerieA, HttpStatusCode.Accepted, responseHeaders)
                        }

                        request.url.encodedPath.contains("/fixtures") &&
                            request.url.encodedQuery.contains("league=39") -> {
                            respond(matchesPremier, HttpStatusCode.Accepted, responseHeaders)
                        }

                        request.url.encodedPath.contains("/fixtures") -> {
                            respond(matchesEmpty, HttpStatusCode.Accepted, responseHeaders)
                        }

                        else -> {
                            error("Unhandled ${request.url}")
                        }
                    }
                }
            }
        }
    }
}
