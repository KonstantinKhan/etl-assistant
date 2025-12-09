package com.khan366kos.bff.auth

import io.ktor.client.*
import io.ktor.client.plugins.api.*
import io.ktor.client.request.*
import io.ktor.http.*

class AuthPluginConfig {
    var baseUrl: String = ""
    lateinit var tokenManager: TokenManager
}

val AuthPlugin = createClientPlugin("AuthPlugin", ::AuthPluginConfig) {
    val tokenManager = pluginConfig.tokenManager
    val baseUrl = pluginConfig.baseUrl

    // Use on(Send) to intercept request/response cycle
    on(Send) { request ->
        // Get valid token (with proactive refresh at 80% expiration)
        val token = tokenManager.getValidToken(client, baseUrl)

        // Add Authorization header
        request.headers.append(HttpHeaders.Authorization, "Bearer $token")

        // Execute original request
        val originalCall = proceed(request)

        // Check response status
        if (originalCall.response.status == HttpStatusCode.Unauthorized) {
            // Force re-authentication
            tokenManager.authenticate(client, baseUrl)
            val newToken = tokenManager.getValidToken(client, baseUrl)

            // Update header and retry
            request.headers.remove(HttpHeaders.Authorization)
            request.headers.append(HttpHeaders.Authorization, "Bearer $newToken")

            // Retry request
            proceed(request)
        } else {
            originalCall
        }
    }
}

class AuthenticationException(message: String) : Exception(message)