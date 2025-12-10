package com.khan366kos.bff.auth

import io.ktor.client.plugins.api.*
import io.ktor.http.*

class AuthPluginConfig {
    var baseUrl: String = ""
    lateinit var tokenManager: TokenManager
}

val AuthPlugin = createClientPlugin("AuthPlugin", ::AuthPluginConfig) {
    val tokenManager = pluginConfig.tokenManager
    val baseUrl = pluginConfig.baseUrl

    on(Send) { request ->
        if (!request.url.toString().contains("login/sign-in")) {

            val token = tokenManager.getValidToken(client, baseUrl)

            request.headers.append(HttpHeaders.Authorization, "Bearer $token")

            val originalCall = proceed(request)

            if (originalCall.response.status == HttpStatusCode.Unauthorized) {
                tokenManager.authenticate(client, baseUrl)
                val newToken = tokenManager.getValidToken(client, baseUrl)

                request.headers.remove(HttpHeaders.Authorization)
                request.headers.append(HttpHeaders.Authorization, "Bearer $newToken")

                proceed(request)
            } else {
                originalCall
            }
        } else {
            proceed(request)
        }
    }
}

class AuthenticationException(message: String) : Exception(message)