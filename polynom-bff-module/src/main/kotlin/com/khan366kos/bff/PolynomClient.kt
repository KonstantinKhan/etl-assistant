package com.khan366kos.bff

import com.khan366kos.bff.auth.AuthPlugin
import com.khan366kos.bff.auth.TokenManager
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.engine.cio.*
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

    private val tokenManager = TokenManager()

    val client = HttpClient(CIO) {
        install(ContentNegotiation) {
            json()
        }
        install(DefaultRequest) {
            url(BASE_URL)
        }
        install(AuthPlugin) {
            baseUrl = BASE_URL
            tokenManager = this@PolynomClient.tokenManager
        }
    }

    suspend inline fun <reified T> get(url: String): T {
        return client.get(url).body()
    }

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