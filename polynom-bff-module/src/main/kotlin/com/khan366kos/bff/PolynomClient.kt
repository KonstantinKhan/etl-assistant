package com.khan366kos.bff

import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.plugins.DefaultRequest
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*

class PolynomClient {
    companion object {
        private const val DEFAULT_HOST = "127.0.0.1"
        private const val DEFAULT_PORT = "5100"

        private val SERVER_HOST = System.getenv("SERVER_HOST") ?: System.getProperty("server.host", DEFAULT_HOST)
        private val SERVER_PORT = System.getenv("SERVER_PORT") ?: System.getProperty("server.port", DEFAULT_PORT)
        private const val BASE_API_PATH = "/api/v1"
        private val BASE_URL = "http://$SERVER_HOST:$SERVER_PORT$BASE_API_PATH"
    }

    val client = HttpClient {
        install(ContentNegotiation) {
            json()
        }
        install(DefaultRequest) {
            url(BASE_URL)
        }
        // Common configuration can be added here
    }

    suspend inline fun <reified T> get(url: String): T {
        return client.get(url).body()
    }

    /**
     * Makes a POST request to the specified URL with the provided data and returns the response body
     */
    suspend inline fun <reified T> post(url: String, data: Any): T {
        return client.post(url) {
            contentType(ContentType.Application.Json)
            setBody(data)
        }.body()
    }

    fun close() {
        client.close()
    }
}

fun createSimpleBffClient(): PolynomClient = PolynomClient()