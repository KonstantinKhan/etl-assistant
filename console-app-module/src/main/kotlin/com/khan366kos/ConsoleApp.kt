package com.khan366kos

import com.khan366kos.bff.PolynomClient
import com.khan366kos.bff.createSimpleBffClient
import kotlinx.coroutines.runBlocking

fun main() = runBlocking {
    val polynomClient: PolynomClient = createSimpleBffClient()

    try {
        val response = polynomClient.allReference()
        println("response: $response")
    } catch (e: Exception) {
        println("Error calling API: ${e.message}")
    } finally {
        polynomClient.close()
    }
}