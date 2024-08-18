package com.diegopizzo.core.config

import androidx.annotation.VisibleForTesting
import io.ktor.client.HttpClient
import io.ktor.client.engine.mock.MockEngine
import io.ktor.client.engine.mock.respond
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.request.accept
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.http.HttpStatusCode
import io.ktor.http.contentType
import io.ktor.http.headersOf
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

private fun mockEngine(
    jsonResponse: String? = null,
    httpStatusCode: HttpStatusCode = HttpStatusCode.OK,
) = MockEngine {
    respond(
        content = jsonResponse ?: "",
        status = httpStatusCode,
        headers = headersOf(HttpHeaders.ContentType, "application/json"),
    )
}

@VisibleForTesting
fun mockHttpClient(
    jsonResponse: String? = null,
    httpStatusCode: HttpStatusCode = HttpStatusCode.OK,
) = HttpClient(
    engine = mockEngine(jsonResponse, httpStatusCode),
) {
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

    defaultRequest {
        contentType(ContentType.Application.Json)
        accept(ContentType.Application.Json)
        url(BASE_URL)
    }
}
