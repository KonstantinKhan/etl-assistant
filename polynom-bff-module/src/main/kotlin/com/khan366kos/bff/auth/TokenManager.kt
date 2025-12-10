package com.khan366kos.bff.auth

import com.khan366kos.bff.config.AuthConfig
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.http.*
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import java.util.concurrent.atomic.AtomicReference

class TokenManager {
    private val tokenData = AtomicReference<TokenData?>(null)
    private val authMutex = Mutex()

    fun needsRefresh(): Boolean {
        val token = tokenData.get() ?: return true
        val now = System.currentTimeMillis()
        val expirationTime = token.expiresAt
        val threshold = expirationTime - (token.expiresAt - now) / (1 - AuthConfig.REFRESH_THRESHOLD)
        return now >= threshold
    }

    suspend fun getValidToken(httpClient: HttpClient, baseUrl: String): String {
        if (!needsRefresh()) {
            return tokenData.get()?.accessToken ?: error("Token unexpectedly null")
        }

        authMutex.withLock {
            if (!needsRefresh()) {
                return tokenData.get()?.accessToken ?: error("Token unexpectedly null")
            }

            performAuthentication(httpClient, baseUrl)
            return tokenData.get()?.accessToken ?: error("Authentication failed")
        }
    }

    suspend fun authenticate(httpClient: HttpClient, baseUrl: String) {
        authMutex.withLock {
            performAuthentication(httpClient, baseUrl)
        }
    }

    private suspend fun performAuthentication(httpClient: HttpClient, baseUrl: String) {
        val loginRequest = LoginRequest(
            storageId = AuthConfig.STORAGE_ID,
            login = AuthConfig.login,
            password = AuthConfig.password
        )

        val response: LoginResponse = httpClient.post("$baseUrl${AuthConfig.LOGIN_ENDPOINT}") {
            contentType(ContentType.Application.Json)
            setBody(loginRequest)
        }.body()

        val expiresAt = System.currentTimeMillis() + (response.expiresIn * 1000L)
        tokenData.set(TokenData(response.accessToken, response.refreshToken, expiresAt))
    }
}