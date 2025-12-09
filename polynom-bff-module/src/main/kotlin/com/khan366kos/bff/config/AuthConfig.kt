package com.khan366kos.bff.config

object AuthConfig {
    const val STORAGE_ID = "0f702cff-3d47-44f6-aabc-b679a2e788ec"
    const val LOGIN_ENDPOINT = "/login/sign-in"
    const val REFRESH_THRESHOLD = 0.8

    val login: String = System.getenv("LOGIN")
        ?: throw IllegalStateException("LOGIN environment variable not set")

    val password: String = System.getenv("PASSWORD")
        ?: throw IllegalStateException("PASSWORD environment variable not set")
}