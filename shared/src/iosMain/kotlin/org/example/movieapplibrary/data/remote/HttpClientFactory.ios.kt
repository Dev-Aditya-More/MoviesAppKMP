package org.example.movieapplibrary.data.remote

import io.ktor.client.HttpClient

actual fun createHttpClient(token: String): HttpClient =
    HttpClient(Darwin) {
        install(ContentNegotiation) {
            json(Json { ignoreUnknownKeys = true })
        }
        defaultRequest {
            header("Authorization", "Bearer $token")
        }
    }
