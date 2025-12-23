package com.khan366kos.bff

import StorageTransport
import com.khan366kos.bff.auth.AuthPlugin
import com.khan366kos.bff.auth.TokenManager
import com.khan366kos.common.models.business.ObjectInfo
import com.khan366kos.common.models.simple.GroupId
import com.khan366kos.common.responses.ElementResponse
import com.khan366kos.common.responses.PropertyOwnerRespose
import com.khan366kos.common.requests.CreateElementRequest
import com.khan366kos.common.requests.PropertyAssignmentRequest
import com.khan366kos.common.requests.PropertyOwnerRequest
import io.ktor.client.*
import io.ktor.client.call.body
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.DefaultRequest
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.logging.DEFAULT
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.*
import io.ktor.client.statement.bodyAsText
import io.ktor.http.ContentType
import io.ktor.http.URLProtocol
import io.ktor.http.contentType
import io.ktor.http.path
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json


class PolynomClient {
    companion object {
        private const val DEFAULT_HOST = "127.0.0.1"
        private const val DEFAULT_PORT = "5100"
        private const val BASE_API_PATH = "/api/v1"

        private val SERVER_HOST = System.getenv("SERVER_HOST") ?: System.getProperty("server.host", DEFAULT_HOST)
        private val SERVER_PORT = System.getenv("SERVER_PORT") ?: System.getProperty("server.port", DEFAULT_PORT)
        private val BASE_URL = "https://$SERVER_HOST:$SERVER_PORT"
    }

    private val tokenManager = TokenManager()

    val client = HttpClient(CIO) {
        install(ContentNegotiation) {
            json(Json {
                ignoreUnknownKeys = true
            })
        }
        install(DefaultRequest) {
            url {
                protocol = URLProtocol.HTTPS
                host = SERVER_HOST
                port = SERVER_PORT.toInt()
                path("$BASE_API_PATH/")
            }
            contentType(ContentType.Application.Json)
        }
        install(AuthPlugin) {
            baseUrl = "$BASE_URL$BASE_API_PATH"
            tokenManager = this@PolynomClient.tokenManager
        }
        install(Logging) {
            logger = Logger.DEFAULT
            level = LogLevel.HEADERS
        }
        install(HttpTimeout) {
            requestTimeoutMillis = 30_000
        }
    }

    suspend fun storageDefinitions(): ArrayList<StorageTransport> = client.get("login/storage-definitions").body()

    suspend fun allReference(): String {
        return client.get("reference/all").bodyAsText()
    }

    suspend fun element(request: CreateElementRequest): ElementResponse {
        return client.post("element") {
            setBody(request)
        }.body()
    }

    suspend fun propertyOwner(request: PropertyOwnerRequest): PropertyOwnerRespose {
        return client.post("property-owner/properties") {
            setBody(request.identifier)
        }.body()
    }

    suspend fun setPropertyValues(request: PropertyAssignmentRequest): String {
        return client.put("property-owner/set-property-values") {
            setBody(request)
        }.bodyAsText()
    }

    suspend fun elementByGroup(groupId: GroupId): List<ObjectInfo> {
        return client.get("element/by-element-group") {
            parameter("elementGroupId", groupId.groupId)
        }.body()
    }


    fun close() {
        client.close()
    }
}

fun createSimpleBffClient(): PolynomClient = PolynomClient()