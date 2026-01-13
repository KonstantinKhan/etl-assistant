package com.khan366kos.bff.config

object AuthConfig {
    const val STORAGE_ID = "99a9f2bc-0337-4774-8c5a-0d42331d156c"
    const val LOGIN_ENDPOINT = "/login/sign-in"
    const val REFRESH_THRESHOLD = 0.8

    val login: String = System.getenv("LOGIN")
        ?: throw IllegalStateException("LOGIN environment variable not set")

    val password: String = System.getenv("PASSWORD")
        ?: throw IllegalStateException("PASSWORD environment variable not set")
}